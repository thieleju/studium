#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/resource.h>
#include <sys/wait.h>
#include <time.h>
#include "primeArray.h"

// Anzahl der Kindsprozesse
#define NUM_PROCESSES 4

/*
 * Faktorisiert eine Zahl in zwei Faktoren.
 * @param number Zahl, die faktorisiert werden soll
 * @param factor1 Pointer auf die erste gefundene Zahl
 * @param factor2 Pointer auf die zweite gefundene Zahl
 */
void factors(unsigned long long number, unsigned long long *factor1, unsigned long long *factor2)
{
  unsigned long long factor = 899999999;
  while (number > 1)
  {
    if (number % factor == 0)
    {
      *factor1 = factor;
      *factor2 = number / factor;
      break;
    }
    factor++;
  }
}

/*
 * Gibt die aktuelle Zeit in Sekunden zurück.
 * @return aktuelle Zeit
 */
double get_time()
{
  struct rusage usage;
  getrusage(RUSAGE_SELF, &usage);
  return usage.ru_utime.tv_sec + (usage.ru_utime.tv_usec / 1000000.0);
}

/*
 * Erstellt die Kindprozesse und weist jedem Kindprozess einen Bereich des PrimeArrays zu.
 * @param pipe_fd Pipes für die Kommunikation zwischen Eltern- und Kindprozess
 * @param time_pipe_fd Pipe für die Zeiten der Kindprozesse
 * @param numbersPerProcess Anzahl der Zahlen, die jeder Kindprozess faktorisieren soll
 */
void create_child_processes(int pipe_fd[][2], int time_pipe_fd[], int numbersPerProcess)
{
  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    pid_t pid = fork();

    if (pid == -1)
    {
      perror("Failed to fork");
      exit(1);
    }

    if (pid == 0)
    {
      // Childprocess
      for (int j = 0; j < NUM_PROCESSES; j++)
      {
        close(pipe_fd[j][0]);
        if (j != i)
        {
          // Close write end of pipes, that are not used by this child process
          // This ensures that each child process only communicates with its own pipe
          close(pipe_fd[j][1]);
        }
      }

      // Assign number ranges to child processes
      int start = i * numbersPerProcess;
      int end = start + numbersPerProcess;

      double start_time = get_time();

      for (int j = start; j < end; j++)
      {
        unsigned long long number = primeArray[j][0];
        unsigned long long factor1, factor2;

        printf("-> Kindprozess %d: Faktorisiere Zahl %d/%d: \t%llu\n", i, j, end, number);

        double start_time_inner = get_time();

        factors(number, &factor1, &factor2);

        double elapsed_time_inner = get_time() - start_time_inner;

        printf("-> Kindprozess %d hat Zahl %llu in %.6f Sekunden faktorisiert\n", i, number, elapsed_time_inner);

        write(pipe_fd[i][1], &factor1, sizeof(unsigned long long));
        write(pipe_fd[i][1], &factor2, sizeof(unsigned long long));
      }

      double end_time = get_time();
      double elapsed_time = end_time - start_time;

      // Write measured time to time pipe
      write(time_pipe_fd[1], &elapsed_time, sizeof(double));

      printf("==> FINISHED Kindprozess %d: %.6f Sekunden, Zahlen %d bis %d\n", i, elapsed_time, start, end);

      // Close write end of pipes
      close(pipe_fd[i][1]);
      close(time_pipe_fd[1]);
      exit(0);
    }
  }
}

/*
 * Empfängt die Faktoren von den Kindprozessen und speichert diese im PrimeArray.
 * @param pipe_fd Pipes für die Kommunikation zwischen Eltern- und Kindprozess
 * @param time_pipe_fd Pipe für die Zeiten der Kindprozesse
 * @param numbersPerProcess Anzahl der Zahlen, die jeder Kindprozess faktorisieren soll
 */
void parent_receive_from_children(int pipe_fd[][2], int time_pipe_fd[], int numbersPerProcess)
{
  unsigned long long factor1, factor2;
  double elapsed_time_sum = 0.0;

  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    int start = i * numbersPerProcess;
    int end = start + numbersPerProcess;
    for (int j = start; j < end; j++)
    {
      read(pipe_fd[i][0], &factor1, sizeof(unsigned long long));
      read(pipe_fd[i][0], &factor2, sizeof(unsigned long long));
      primeArray[j][1] = factor1;
      primeArray[j][2] = factor2;
    }
    // Close read end of pipe
    close(pipe_fd[i][0]);

    // Read measured time from time pipe
    double elapsed_time;
    read(time_pipe_fd[0], &elapsed_time, sizeof(double));
    elapsed_time_sum += elapsed_time;

    // wait for child process to finish
    wait(NULL);
  }

  // Print factor list 
  printFactorlist();

  // Calculate average time per child process
  double average_time = elapsed_time_sum / NUM_PROCESSES;
  printf("Durchschnittliche Zeit pro Kindprozess: %.6f Sekunden\n", average_time);
}

/*
 * Main-Method
 */
int main()
{
  int pipe_fd[NUM_PROCESSES][2]; 
  int time_pipe_fd[2];
  int numbersPerProcess = SIZE / NUM_PROCESSES;

  setupArray();

  // pipes for factors
  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    if (pipe(pipe_fd[i]) == -1)
    {
      perror("Failed to create pipe");
      exit(1);
    }
  }

  // pipe for time measurement
  if (pipe(time_pipe_fd) == -1)
  {
    perror("Failed to create pipe");
    exit(1);
  }

  // create child processes
  create_child_processes(pipe_fd, time_pipe_fd, numbersPerProcess);

  // Close write end of pipes
  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    close(pipe_fd[i][1]);
  }
  close(time_pipe_fd[1]);

  // Receive data from child processes
  parent_receive_from_children(pipe_fd, time_pipe_fd, numbersPerProcess);

  return 0;
}
