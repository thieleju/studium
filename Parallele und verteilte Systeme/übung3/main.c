#include <stdio.h>
#include <stdlib.h>

// build command: gcc -o programm main.c

void vertauscheMitPointern(int *px, int *py)
{
  int temp = *px;
  *px = *py;
  *py = temp;
}

int main()
{
  int x = 5;
  int *myPointer = &x;

  *myPointer = 10; // Aus Aufgabe 2

  printf("Aufgabe 1 und 2: \n");
  printf("%d.\n", x);
  printf("%d.\n", myPointer);
  printf("%p.\n", &x);
  printf("%p.\n", &myPointer);
  printf("%d.\n", *myPointer);
  // printf("%d.\n", *x); operand of '*' must be a pointer but has type 'int'

  printf("\nAufgabe 3:\n");

  int a = 5;  // eigentlich x
  int b = 10; // eigentlich y
  printf("Normal: \ta = %d, b = %d\n", a, b);
  vertauscheMitPointern(&a, &b);
  printf("Vertauscht: \ta = %d, b = %d\n", a, b);

  return 0;
}