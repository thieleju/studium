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

  // Aufgabe 5
  printf("\nAufgabe 5:\n");
  int *array = (int *)malloc(256 * sizeof(int));
  if (array == NULL)
  {
    printf("Fehlgeschlagen!\n");
    return 1;
  }
  for (int i = 0; i < 256; i++)
  {
    array[i] = i;
    printf("%d ", array[i]);
  }
  free(array);

  // Aufgabe 7
  printf("\n\nAufgabe 7:\n");
  double *array2 = (double *)malloc(256 * sizeof(double));
  int i = 100;
  array2[0] = i;
  printf("%f\n", array2[0]);
  free(array2);

  // Aufgabe 8
  printf("\nAufgabe 8:\n");
  int count = 0;
  int size_memory = 100 * 1024 * 1024;           // 100 MiB
  int int_count_max = 1024 * 1024 / sizeof(int); // 262144
  while (1)
  {
    int *arr = (int *)malloc(size_memory);
    if (arr == NULL)
    {
      printf("Abbruch, da malloc fuer weitere %d MiB nicht moeglich\n", size_memory / 1024 / 1024);
      break;
    }

    for (int i = 0; i < int_count_max; i++)
    {
      arr[i] = i;
    }

    count++;

    // free(arr);
    // arr = NULL;

    if (count % 100 == 0)
      printf("Durchgang %d\n", count);
  }

  printf("Es konnten %d mal 100 MiB alloziert werden.\n", count);
  printf("Insgesamt:\t%d MiB alloziert.\n", count * 100);
  printf("\t\t%d GiB\n", count * 100 / 1024);

  return 0;
}