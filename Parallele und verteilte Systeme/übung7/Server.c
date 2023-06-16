/*
 ============================================================================
 Name        : SD_PVS_23_U07_server.c
 Author      : Prof. Dr.-Ing. Alexander Biedermann
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

typedef struct
{
    int *primes;
    int count;
    int size;
} PrimeList;

void generatePrimes(int n, PrimeList *primeList)
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

    while (primeList->count < n)
    {
        int isPrime = 1;
        for (int i = 0; i < primeList->count; i++)
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

    while (1)
    {
        // Primzahlindex vom Client empfangen
        int primeIndex;
        char buffer[bufferSize];
        memset(buffer, 0, sizeof(buffer));
        recv(connectionfd, buffer, sizeof(buffer), 0);
        primeIndex = atoi(buffer);

        // Überprüfen, ob der Client die Übertragung beenden möchte
        if (strncmp(buffer, "exit", 4) == 0)
        {
            printf("Client closed connection.\n");
            break;
        }
        printf("Primeindex: %d\n", primeIndex);
        if(primeIndex < 1)
        {
            printf("-> Invalid prime index %d\n", primeIndex);

            char response[bufferSize];
            snprintf(response, bufferSize, "Invalid prime index %d", primeIndex);
            return;
        }

        // Überprüfen, ob die gewünschte Primzahl in der Liste vorhanden ist
        if (primeIndex >= 1 && primeIndex <= primeList.count)
        {
            printf("-> Returning old prime %d\n", primeIndex);

            int primeNumber = primeList.primes[primeIndex - 1];
            char response[bufferSize];
            snprintf(response, bufferSize, "%d", primeNumber);

            // Primzahl an den Client senden
            send(connectionfd, response, sizeof(response), 0);
        }
        else
        {
            // Primzahlen generieren, um die gewünschte Primzahl zu erhalten
            printf("-> Generating new primes to number %d\n", primeIndex);
            generatePrimes(primeIndex, &primeList);

            int primeNumber = primeList.primes[primeIndex - 1];
            char response[bufferSize];
            snprintf(response, bufferSize, "%d", primeNumber);

            // Primzahl an den Client senden
            send(connectionfd, response, sizeof(response), 0);
        }
    }
    // Speicher freigeben
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
    printf("Server listening.\n");

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

    exit(EXIT_SUCCESS);
}
