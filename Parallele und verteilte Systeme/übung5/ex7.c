#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>

void print_fibonacci(int n)
{
  int first = 0, second = 1, next, i;
  if (n == 1)
  {
    printf("%d", first);
  }
  else if (n >= 2)
  {
    printf("%d %d ", first, second);

    for (i = 3; i <= n; i++)
    {
      next = first + second;
      printf("%d ", next);
      first = second;
      second = next;
    }
  }
  printf("\n");
}

void print_powers_of_two(int n)
{
  int i, p = 1;
  for (i = 0; i < n; i++)
  {
    printf("%d ", p);
    p *= 2;
  }
  printf("\n");
}

int is_prime(int n)
{
  int i;
  if (n < 2)
    return 0;
  for (i = 2; i * i <= n; i++)
  {
    if (n % i == 0)
      return 0;
  }
  return 1;
}

void print_primes(int n)
{
  int i, count = 0, num = 2;
  while (count < n)
  {
    if (is_prime(num))
    {
      printf("%d ", num);
      count++;
    }
    num++;
  }
  printf("\n");
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
  struct rusage usage;

  pid1 = fork();

  if (pid1 == -1)
  {
    perror("fork");
    exit(EXIT_FAILURE);
  }
  else if (pid1 == 0)
  { // child process 1
    printf("Fibonacci series:\n");

    double start_time = get_time();
    print_fibonacci(100);
    double end_time = get_time();

    printf("\nFibonacci process CPU time: %f seconds\n\n", end_time - start_time);
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
      printf("Powers of two:\n");

      double start_time = get_time();
      print_powers_of_two(10);
      double end_time = get_time();

      printf("\nPower of two process CPU time: %f seconds\n\n", end_time - start_time);

      exit(EXIT_SUCCESS);
    }
    else
    { // parent process
      printf("Primes:\n");

      double start_time = get_time();
      print_primes(50);
      double end_time = get_time();

      printf("\nPrimes process CPU time: %f seconds\n\n", end_time - start_time);

      waitpid(pid1, &status, 0);
      waitpid(pid2, &status, 0);
    }
  }

  return 0;
}
