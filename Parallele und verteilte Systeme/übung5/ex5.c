#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/resource.h>

int main()
{
  pid_t child_pid, grandchild_pid;

  // Erzeugt einen Kindprozess
  child_pid = fork();

  if (child_pid == -1)
  {
    perror("fork");
    exit(EXIT_FAILURE);
  }
  else if (child_pid == 0)
  {
    // Wir sind im Kindprozess
    printf("Kindprozess: PID = %d, PPID = %d\n", getpid(), getppid());

    // Erzeugt einen Enkelprozess
    grandchild_pid = fork();

    if (grandchild_pid == -1)
    {
      perror("fork");
      exit(EXIT_FAILURE);
    }
    else if (grandchild_pid == 0)
    {
      // Enkelprozess
      printf("Enkelprozess: PID = %d, PPID = %d\n", getpid(), getppid());
      exit(EXIT_SUCCESS);
    }
    else
    {
      // Kindprozess
      wait(NULL);
      exit(EXIT_SUCCESS);
    }
  }
  else
  {
    // Elternprozess
    printf("Elternprozess: PID = %d\n", getpid());
    wait(NULL);
    exit(EXIT_SUCCESS);
  }
}
