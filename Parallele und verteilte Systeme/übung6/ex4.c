#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>
#include <math.h>

#define READ 0
#define WRITE 1

void generate_primes_fast(int n)
{
  int *primes;
  primes = (int *)malloc(n * sizeof(int));

  if (primes == NULL)
  {
    printf("Error: malloc() failed\n");
    exit(EXIT_FAILURE);
  }

  primes[0] = 2;

  int found_primes = 1;
  int current = 3;
  int is_prime;

  while (found_primes != n)
  {
    is_prime = 1;
    for (int i = 0; i < sqrt(found_primes); i++)
    {
      if (current % primes[i] == 0)
      {
        is_prime = 0;
        break;
      }
    }
    if (is_prime)
    {
      primes[found_primes++] = current;
    }
    current += 2;
  }
  printf("-> Largest prime: %i (fast)\n", primes[n - 1]);
  free(primes);
  primes = NULL;
}

void generate_primes_slow(int n)
{
  int *primes;
  primes = (int *)malloc(n * sizeof(int));
  if (primes == NULL)
  {
    printf("Error: malloc() failed\n");
    exit(EXIT_FAILURE);
  }

  primes[0] = 2;

  int found_primes = 1;
  int current = 3;
  int is_prime;

  while (found_primes != n)
  {
    is_prime = 1;
    for (int i = 0; i < found_primes / 2; i++)
    {
      if (current % primes[i] == 0)
      {
        is_prime = 0;
        break;
      }
    }
    if (is_prime)
    {
      primes[found_primes++] = current;
    }
    current += 2;
  }
  printf("-> Largest prime: %i (slow)\n", primes[n - 1]);
  free(primes);
  primes = NULL;
}

double get_time()
{
  struct rusage usage;
  getrusage(RUSAGE_SELF, &usage);
  return usage.ru_utime.tv_sec + (usage.ru_utime.tv_usec / 1000000.0);
}

int main()
{
  pid_t pid1, pid2;
  int status;

  int primNum = 10000;
  int pipeP1[2], pipeP2[2], pipeP3[2], pipeP4[2];

  pipe(pipeP1);
  pipe(pipeP2);
  pipe(pipeP3);
  pipe(pipeP4);

  pid1 = fork();

  if (pid1 == -1)
  {
    exit(EXIT_FAILURE);
  }
  else if (pid1 == 0)
  { // child process 1
    int x;

    close(pipeP1[WRITE]);
    read(pipeP1[READ], &x, sizeof(int));
    close(pipeP1[READ]);

    printf("Read from pipe P1: %i\n", x);

    double start_time = get_time();
    generate_primes_fast(x);
    double diff = get_time() - start_time;

    close(pipeP3[READ]);
    write(pipeP3[WRITE], &diff, sizeof(double));
    close(pipeP3[WRITE]);

    exit(EXIT_SUCCESS);
  }
  else
  {
    // parent process
    pid2 = fork();
    if (pid2 == -1)
    {
      exit(EXIT_FAILURE);
    }
    else if (pid2 == 0)
    { // child process 2
      int x;

      close(pipeP2[WRITE]);
      read(pipeP2[READ], &x, sizeof(int));
      close(pipeP2[READ]);

      printf("Read from pipe P2: %i\n", x);

      double start_time = get_time();
      generate_primes_slow(x);
      double diff = get_time() - start_time;

      close(pipeP4[READ]);
      write(pipeP4[WRITE], &diff, sizeof(double));
      close(pipeP4[WRITE]);

      exit(EXIT_SUCCESS);
    }
    else
    { // parent process
      close(pipeP1[READ]);
      write(pipeP1[WRITE], &primNum, sizeof(int));
      close(pipeP1[WRITE]);

      printf("Wrote to pipe P1: %i\n", primNum);

      close(pipeP2[READ]);
      write(pipeP2[WRITE], &primNum, sizeof(int));
      close(pipeP2[WRITE]);

      printf("Wrote to pipe P2: %i\n", primNum);

      double diff1, diff2;

      close(pipeP3[WRITE]);
      read(pipeP3[READ], &diff1, sizeof(double));
      close(pipeP3[READ]);

      close(pipeP4[WRITE]);
      read(pipeP4[READ], &diff2, sizeof(double));
      close(pipeP4[READ]);

      printf("-> Fast: %f\n", diff1);
      printf("-> Slow: %f\n", diff2);

      waitpid(pid1, &status, 0);
      waitpid(pid2, &status, 0);

      printf("\nBoth processes finished.\n\n");
    }
  }

  return 0;
}
