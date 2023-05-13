#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>
#include <math.h>

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

  pid1 = fork();

  if (pid1 == -1)
  {
    perror("fork");
    exit(EXIT_FAILURE);
  }
  else if (pid1 == 0)
  { // child process 1
    double start_time = get_time();
    generate_primes_fast(50000);
    double end_time = get_time();

    printf("\nFast CPU time: %f seconds\n\n", end_time - start_time);
    exit(EXIT_SUCCESS);
  }
  else
  {
    // parent process
    pid2 = fork();
    if (pid2 == -1)
    {
      perror("fork");
      exit(EXIT_FAILURE);
    }
    else if (pid2 == 0)
    { // child process 2
      double start_time = get_time();
      generate_primes_slow(50000);
      double end_time = get_time();

      printf("\nSlow CPU time: %f seconds\n\n", end_time - start_time);

      exit(EXIT_SUCCESS);
    }
    else
    { // parent process
      waitpid(pid1, &status, 0);
      waitpid(pid2, &status, 0);

      printf("\nBoth processes finished.\n\n");
    }
  }

  return 0;
}
