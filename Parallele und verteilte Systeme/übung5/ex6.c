#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>

void print_fibonacci(long n)
{
  long first = 0, second = 1, next, i;
  if (n == 1)
  {
    printf("%lu", first);
  }
  else if (n >= 2)
  {
    printf("%lu %lu ", first, second);

    for (i = 3; i <= n; i++)
    {
      next = first + second;
      printf("%lu ", next);
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

  pid1 = fork();

  if (pid1 == -1)
  {
    perror("fork");
    exit(EXIT_FAILURE);
  }
  else if (pid1 == 0)
  { // child process 1
    printf("Fibonacci series:\n");
    print_fibonacci(100);
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
      print_powers_of_two(10);
      exit(EXIT_SUCCESS);
    }
    else
    { // parent process
      printf("Primes:\n");
      print_primes(50);

      waitpid(pid1, &status, 0);
      waitpid(pid2, &status, 0);
    }
  }

  return 0;
}
