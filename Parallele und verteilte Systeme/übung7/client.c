/*
 ============================================================================
 Name        : SD_PVS_22_U08_client_template.c
 Author      : Prof. Dr.-Ing. Alexander Biedermann
 Version     : v0.1
 Copyright   : Prof. Dr.-Ing. Alexander Biedermann
 Description : Simple TCP client
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <string.h>

#define port 8080
#define bufferSize 128

// Command to compile: gcc -o client client.c

void exchangeSomeData(int connectionfd)
{
	char readBuffer[bufferSize];
	while (1)
	{
		printf("System: Enter N of the Nth prime you want (type exit to close): ");
		fgets(readBuffer, sizeof(readBuffer), stdin);
		printf("You: %s", readBuffer);

		// Remove trailing newline
		readBuffer[strcspn(readBuffer, "\n")] = '\0';

		// Send N to server
		send(connectionfd, readBuffer, sizeof(readBuffer), 0);

		// Receive prime number from server
		memset(readBuffer, 0, sizeof(readBuffer));
		recv(connectionfd, readBuffer, sizeof(readBuffer), 0);

		printf("Server: %s\n", readBuffer);

		if (strcmp(readBuffer, "bye") == 0)
		{
			break;
		}
	}
}

int main()
{
	// create a socket
	int connectionfd = socket(AF_INET, SOCK_STREAM, 0);
	if (connectionfd == -1)
	{
		printf("Failed to create socket\n");
		exit(EXIT_FAILURE);
	}
	printf("System: Socket created.\n");

	// set server IP and port
	struct sockaddr_in serverSockAddr;
	serverSockAddr.sin_family = AF_INET;
	serverSockAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverSockAddr.sin_port = htons(port);

	// Connect client and server sockets
	if (connect(connectionfd, (struct sockaddr *)&serverSockAddr, sizeof(serverSockAddr)) != 0)
	{
		printf("Failed to connect to server.\n");
		exit(EXIT_FAILURE);
	}
	printf("System: Connected to server.\n");

	// function for chat
	exchangeSomeData(connectionfd);

	// close the socket
	close(connectionfd);
	printf("System: Socket closed.\n");

	exit(EXIT_SUCCESS);
}
