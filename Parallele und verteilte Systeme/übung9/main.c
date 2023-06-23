#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/resource.h>
#include <sys/wait.h>
#include <time.h>
#include <pthread.h>
#include "primeArray.h"

#define NUM_THREADS 128

// Command to compile:  gcc -o main -pthread  main.c

void* doWork(void* arg)
{
  // Extract int variable from void* parameter
  int i = *(int*)arg;
  unsigned long long number = primeArray[i][0];
  unsigned long long factor1, factor2;

  printf("-> Thread %d: Faktorisiere Zahl %llu\n",i,number);

  double start_time_inner = get_time();

  factors(number, &factor1, &factor2);

  double elapsed_time_inner = get_time() - start_time_inner;

  printf("-> Thread %d hat Zahl %llu in %.6f Sekunden faktorisiert\n", i,number, elapsed_time_inner);

  primeArray[i][1] = factor1;
  primeArray[i][2] = factor2;

  // terminate thread
  pthread_exit(NULL);
}

int main()
{
  pthread_t threads[NUM_THREADS];
  int indices[NUM_THREADS];

  setupArray();

  for (int i = 0; i < NUM_THREADS; i++) {
    indices[i] = i;

    if (pthread_create(&threads[i], NULL, doWork, (void*)&indices[i]))
    {
      perror("Error creating thread\n");
      return 1;
    }

    printf("Thread %d created\n", i);
  }

 // wait for all threads to finish
  for (int i = 0; i < NUM_THREADS; i++) {
    if (pthread_join(threads[i], NULL))
    {
      perror("Error joining thread\n");
      return 1;
    }
  }

  printFactorlist();

  return 0;
}
