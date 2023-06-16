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
  // Faktoren der Zahl number finden
  unsigned long long factor = 2;

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
 * Gibt die aktuelle Zeit zurück.
 * @return aktuelle Zeit
 */
double get_time()
{
  struct timespec time;
  clock_gettime(CLOCK_MONOTONIC, &time);
  return time.tv_sec + (time.tv_nsec / 1000000000.0);
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
      perror("Fehler beim Forken");
      exit(1);
    }

    if (pid == 0)
    {
      // Kindsprozess
      for (int j = 0; j < NUM_PROCESSES; j++)
      {
        close(pipe_fd[j][0]);
        if (j != i)
        {
          // Schreibende der Pipe wird nur für die Pipes geschlossen,
          // die nicht von diesem spezifischen Kindsprozess verwendet werden.
          // -> sicherstellen, dass jeder Kindsprozess nur mit seiner eigenen Pipe kommuniziert.
          close(pipe_fd[j][1]);
        }
      }

      // Zahlen von Start bis Ende des zugewiesenen Bereichs faktorisieren
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

      // Verbrauchte Zeit in die Zeit-Pipe schreiben
      write(time_pipe_fd[1], &elapsed_time, sizeof(double));

      printf("==> FINISHED Kindprozess %d: %.6f Sekunden, Zahlen %d bis %d\n", i, elapsed_time, start, end);

      // Pipes schließen
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
    // Lesende Seite der Pipe schließen
    close(pipe_fd[i][0]);

    // Zeit von der Zeit-Pipe lesen
    double elapsed_time;
    read(time_pipe_fd[0], &elapsed_time, sizeof(double));
    elapsed_time_sum += elapsed_time;

    // warten bis Kindprozess beendet ist
    wait(NULL);
  }

  // Ausgabe der Primzahlen mit ihren Faktoren
  printFactorlist();

  // Durchschnittliche Zeit berechnen und ausgeben
  double average_time = elapsed_time_sum / NUM_PROCESSES;
  printf("Durchschnittliche Zeit pro Kindprozess: %.6f Sekunden\n", average_time);
}

/*
 * Main-Methode.
 */
int main()
{
  int pipe_fd[NUM_PROCESSES][2]; // Pipes für Faktoren
  int time_pipe_fd[2];           // Pipe für Zeiten der Kindprozesse
  int numbersPerProcess = SIZE / NUM_PROCESSES;

  setupArray();

  // Pipes für Faktoren erstellen
  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    if (pipe(pipe_fd[i]) == -1)
    {
      perror("Fehler beim Erstellen der Pipe");
      exit(1);
    }
  }

  // Pipe für Zeiten erstellen
  if (pipe(time_pipe_fd) == -1)
  {
    perror("Fehler beim Erstellen der Zeit-Pipe");
    exit(1);
  }

  // Kindprozesse erstellen
  create_child_processes(pipe_fd, time_pipe_fd, numbersPerProcess);

  // Schreibende Seite der Pipes schließen
  for (int i = 0; i < NUM_PROCESSES; i++)
  {
    close(pipe_fd[i][1]);
  }
  close(time_pipe_fd[1]);

  // Daten empfangen und ausgeben
  parent_receive_from_children(pipe_fd, time_pipe_fd, numbersPerProcess);

  return 0;
}
