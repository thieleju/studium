/*
 ============================================================================
 Name        : SD_PVS_23_U07_server.c
 Author      : Prof. Dr.-Ing. Alexander Biedermann, thieleju, chatGPT
 Version     : v0.1
 Copyright   : Prof. Dr.-Ing. Alexander Biedermann
 Description : Simple TCP server
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netinet/in.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>

#define port 8080
#define bufferSize 128

// Command to compile: gcc -o server server.c -lm

typedef struct
{
    int *primes;
    int count;
} PrimeList;

void generate_primes(int n, PrimeList *primeList)
{
    primeList->primes = (int *)malloc(n * sizeof(int));
    if (primeList->primes == NULL)
    {
        printf("Error allocating memory.\n");
        exit(EXIT_FAILURE);
    }

    primeList->count = 0;
    primeList->primes[primeList->count++] = 2;
    int current = 3;

    while (primeList->count != n)
    {
        int isPrime = 1;
        for (int i = 0; i < sqrt(primeList->count); i++)
        {
            if (current % primeList->primes[i] == 0)
            {
                isPrime = 0;
                break;
            }
        }
        if (isPrime)
        {
            primeList->primes[primeList->count++] = current;
        }
        current += 2;
    }
}

void exchangeSomeData(int connectionfd)
{
    char readBuffer[bufferSize];
    PrimeList primeList;
    primeList.count = 0;

    while (1)
    {
        // Receive N from client
        int prime_index;
        char buffer[bufferSize];
        memset(buffer, 0, sizeof(buffer));
        recv(connectionfd, buffer, sizeof(buffer), 0);
        prime_index = atoi(buffer);

        // response buffer
        char response[bufferSize];

        // Check if client wants to close connection
        if (strcmp(buffer, "exit") == 0)
        {
            snprintf(response, bufferSize, "%s", "bye");
            send(connectionfd, response, sizeof(response), 0);
            printf("Client disconnected.\n");
            break;
        }
        else if (prime_index < 1)
        {
            printf("-> Invalid input: %s\n", buffer);
            snprintf(response, bufferSize, "Invalid input");
            send(connectionfd, response, sizeof(response), 0);
        }
        // Check if prime is already in list
        else if (prime_index <= primeList.count)
        {
            printf("Client wants %dth Prime (%d primes in list)\n", prime_index, primeList.count);

            int prime = primeList.primes[prime_index - 1];
            printf("-> Returning %dth prime from list: %d\n", prime_index, prime);

            // Send prime to client
            snprintf(response, bufferSize, "%d", prime);
            send(connectionfd, response, sizeof(response), 0);
        }
        else
        {
            printf("Client wants %dth Prime (%d primes in list)\n", prime_index, primeList.count);

            // Generate new primes
            printf("-> Generating new primes for %dth prime\n", prime_index);
            generate_primes(prime_index, &primeList);

            int prime = primeList.primes[prime_index - 1];

            // Send prime to client
            snprintf(response, bufferSize, "%d", prime);
            send(connectionfd, response, sizeof(response), 0);
        }
    }
    // Free allocated memory
    free(primeList.primes);
    primeList.primes = NULL;
}

int main()
{
    // Create an internet socket, return value is a file descriptor to the socket
    int socketfd = socket(AF_INET, SOCK_STREAM, 0);
    if (socketfd == -1)
    {
        printf("Could not create socket.\n");
        exit(EXIT_FAILURE);
    }
    printf("Socket created.\n");

    // Use SO_REUSEADDR to avoid server crash after restart
    int optval = 1;
    if (setsockopt(socketfd, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval)) != 0)
    {
        printf("Could not set socket options.\n");
        exit(EXIT_FAILURE);
    }

    // Set server type and address
    struct sockaddr_in serverSockAddr;
    serverSockAddr.sin_family = AF_INET;
    serverSockAddr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
    serverSockAddr.sin_port = htons(port);

    // Bind socket to IP
    if (bind(socketfd, (struct sockaddr *)&serverSockAddr,
             sizeof(serverSockAddr)) != 0)
    {
        printf("Could not bind socket\n");
        exit(EXIT_FAILURE);
    }
    printf("Socket bound.\n");

    // Socket listens
    if ((listen(socketfd, 1)) != 0)
    {
        printf("Listening failed.\n");
        exit(EXIT_FAILURE);
    }
    printf("Server listening on port %d.\n", port);

    // Accept package from client
    struct sockaddr_in clientSockAddr;
    int lenClSoAddr = sizeof(clientSockAddr);
    int connectionfd = accept(socketfd, (struct sockaddr *)&clientSockAddr, &lenClSoAddr);
    if (connectionfd < 0)
    {
        printf("Accept from Server failed.\n");
        exit(EXIT_FAILURE);
    }
    printf("Client connection accepted.\n");

    // As there now exists a connection between client and server,
    // they may exchange data
    exchangeSomeData(connectionfd);

    // Closing the socket
    close(socketfd);
    printf("Socket closed.\n");

    exit(EXIT_SUCCESS);
}
