#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/resource.h>

int main()
{
  printf("Hello user %s\n", getlogin());
  printf("You are executing this in directory %s\n", getcwd(NULL, 0));

  struct rusage myProgramStats;

  for (unsigned long i = 0; i < 1000000; i++)
  {
    printf("I'm just here to consume some time ... i is currently %lu\n", i);
  }

  getrusage(RUSAGE_SELF, &myProgramStats);
  printf("CPU time spent: %ld.%06ld\n", myProgramStats.ru_utime.tv_sec, myProgramStats.ru_utime.tv_usec);
}