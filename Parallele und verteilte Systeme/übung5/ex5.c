#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/resource.h>
#include <sys/wait.h>

int main()
{
  pid_t child_pid_1, child_pid_2;

  // Erstes fork()
  child_pid_1 = fork();
  if (child_pid_1 == -1)
  {
    printf("Fehler beim Klonen des ersten Prozesses!\n");
    exit(EXIT_FAILURE);
  }
  else if (child_pid_1 == 0)
  {
    // Code für den Kindprozess
    printf("Kindprozess 1 - ID: %d, Eltern-ID: %d\n", getpid(), getppid());

    // Zweites fork()
    child_pid_2 = fork();
    if (child_pid_2 == -1)
    {
      printf("Fehler beim Klonen des zweiten Prozesses!\n");
      exit(EXIT_FAILURE);
    }
    else if (child_pid_2 == 0)
    {
      // Code für den Enkelprozess
      printf("Enkelprozess - ID: %d, Eltern-ID: %d\n", getpid(), getppid());
      exit(EXIT_SUCCESS);
    }
    else
    {
      // Code für den Kindprozess
      printf("Kindprozess 1 wartet auf Enkelprozess...\n");
      wait(NULL);
      printf("Enkelprozess beendet.\n");
      exit(EXIT_SUCCESS);
    }
  }
  else
  {
    // Code für den Elternprozess
    printf("Elternprozess - ID: %d\n", getpid());
    wait(NULL);
    printf("Alle Prozesse beendet.\n");
    exit(EXIT_SUCCESS);
  }
}
