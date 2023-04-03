#include <stdio.h>
#include <stdlib.h>

// build command: gcc -o programm main.c

int berechneZ(int x, int y)
{
  return x * y;
}

void vertauscheImArray(int myArray[])
{
  int temp = myArray[0];
  myArray[0] = myArray[1];
  myArray[1] = temp;
}

int main()
{
  // Aufgabe 6
  int x = 5;
  int y = 10;
  int z = berechneZ(x, y);
  printf("z hat den Wert: %d.\n", z);

  // Aufgabe 8
  int myArray[2];
  myArray[0] = 5;
  myArray[1] = 10;
  vertauscheImArray(myArray);
  printf("Element 0 hat nun den Wert %d und Element 1 hat den Wert %d.\n", myArray[0], myArray[1]);

  // Bonusaufgabe
  int p = 10, q[p];
  for (; q[--p] = p;)
    ;

  // Bonusaufgabe verbessert
  int r = 10, s[r];
  for (int i = 0; i < r; i++)
  {
    s[i] = i;
  }

  // Ausgabe der beiden Arrays
  for (int i = 0; i < 10; i++)
  {
    printf("q[%d] = %d\t", i, q[i]);
    printf("s[%d] = %d\n", i, s[i]);
  }

  return 0;
}