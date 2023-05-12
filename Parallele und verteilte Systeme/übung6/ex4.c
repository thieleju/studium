#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>

#define READ 0
#define WRITE 1

void generatePrime(int n, int *primes)
{
    int X = 0, i = 2;
    bool flag;
    while(X < n){
        flag = true;
        for(int j = 2; j <= i/2; j++){
            if (i%j == 0){
                flag = false;
                break;
            }
        }
        if(flag){
            primes[X] = i;
            X++;
        }
        i++;
    }
    // print biggest prime
    printf("Largest prime generated: %d\n", primes[n-1]);
}

int main()
{
    int primNum, i;
    int pipeP1[2], pipeP2[2];
    pid_t pid1, pid2;

    if (pipe(pipeP1) == -1 || pipe(pipeP2) == -1)
    {
        perror("Error creating pipes");
        exit(EXIT_FAILURE);
    }

    pid1 = fork();
    if (pid1 == -1)
    {
        perror("Error creating first child process");
        exit(EXIT_FAILURE);
    }
    else if (pid1 == 0)
    {
        // First child process
        close(pipeP1[WRITE]);

        int x;
        if (read(pipeP1[READ], &x, sizeof(x)) == -1)
        {
            perror("Error reading from pipeP1");
            exit(EXIT_FAILURE);
        }
        printf("First child read pipe1: %d\n", x);

        int *primes = malloc(sizeof(int) * x);
        generatePrime(x, primes);

        if (write(pipeP2[WRITE], primes, sizeof(int) * x) == -1)
        {
            perror("Error writing to pipeP2");
            exit(EXIT_FAILURE);
        }

        close(pipeP1[READ]);
        close(pipeP2[WRITE]);
        free(primes);
        exit(EXIT_SUCCESS);
    }
    else
    {
        // Parent process
        pid2 = fork();
        if (pid2 == -1)
        {
            perror("Error creating second child process");
            exit(EXIT_FAILURE);
        }
        else if (pid2 == 0)
        {
            // Second child process
            close(pipeP1[WRITE]);

            int x;
            if (read(pipeP1[READ], &x, sizeof(x)) == -1)
            {
                perror("Error reading from pipeP1");
                exit(EXIT_FAILURE);
            }

            printf("Second child read pipe1: %d\n", x);

            int *primes = malloc(sizeof(int) * x);
            generatePrime(x, primes);

            if (write(pipeP2[WRITE], primes, sizeof(int) * x) == -1)
            {
                perror("Error writing to pipeP2");
                exit(EXIT_FAILURE);
            }

            close(pipeP1[READ]);
            close(pipeP2[WRITE]);
            free(primes);
            exit(EXIT_SUCCESS);
        }
        else
        {
            // Parent process
            close(pipeP1[READ]);
            close(pipeP2[WRITE]);

            printf("Enter the number of prime numbers to generate: ");
            scanf("%d", &primNum);

            if (write(pipeP1[WRITE], &primNum, sizeof(primNum)) == -1)
            {
                perror("Error writing to pipeP1");
                exit(EXIT_FAILURE);
            }

            int *primes1 = malloc(sizeof(int) * primNum);
            if (read(pipeP2[READ], primes1, sizeof(int) * primNum) == -1)
            {
                perror("Error reading from pipeP2");
                exit(EXIT_FAILURE);
            }

            printf("First child process (pid=%d) terminated\n", pid1);
            printf("Second child process (pid=%d) terminated\n", pid2);
            // print largest prime
            printf("Largest prime generated: %d\n", primes1[primNum-1]);
        }
    }
}