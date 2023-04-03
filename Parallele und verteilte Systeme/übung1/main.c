#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include "aufgabe1.h"

// build command: gcc -o programm main.c aufgabe1.c

int main()
{
  // initialize random seed
  srand(time(NULL));

  char c = ' ';

  while (c != 'q' && c != 'Q')
  {
    printRandomAddition();
    printf("Press Enter to continue and type Q to quit\n");
    c = getchar();
  }

  return 0;
}