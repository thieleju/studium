#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#include "aufgabe1.h"

int addition(int a, int b)
{
  return a + b;
}

int randomInt(int min, int max)
{
  return rand() % (max - min + 1) + min;
}

void printRandomAddition()
{
  int a = randomInt(0, 100);
  int b = randomInt(0, 100);
  int c = addition(a, b);

  printf("%i + %i = %i\n", a, b, c);
}