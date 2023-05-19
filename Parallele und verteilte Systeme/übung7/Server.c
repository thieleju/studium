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

#define port 8080
#define bufferSize 128


void exchangeSomeData(int connectionfd) {
	char readBuffer[bufferSize];
	// infinite loop for chat
	while (1) {
		//Read data from client
		bzero(readBuffer, bufferSize);
		read(connectionfd, readBuffer, bufferSize);
		printf("Received from client: %s\n", readBuffer);

		//send data to client
		printf("Type line to be sent to client:");
		size_t len;
		char *line = NULL;
		ssize_t getLineret = getline(&line, &len, stdin);
		write(connectionfd, line, getLineret-1);

		//end transmission, if "exit" is written
		if (!strncmp("exit", line, 4)) {
			printf("Ending Transmission\n");
			free(line);
			break;
		}

		free(line);
	}
}

int main() {
	//Create an internet socket, return value is a file descriptor to the socket
	int socketfd = socket(AF_INET, SOCK_STREAM, 0);
	if (socketfd == -1) {
		printf("Could not create socket.\n");
		exit(EXIT_FAILURE);
	}
	printf("Socket created.\n");


	//Set server type and address
	struct sockaddr_in serverSockAddr;
	serverSockAddr.sin_family = AF_INET;
	serverSockAddr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);
	serverSockAddr.sin_port = htons(port);

	//Bind socket to IP
	if (bind(socketfd, (struct sockaddr*) &serverSockAddr,
			sizeof(serverSockAddr)) != 0) {
		printf("Could not bind socket\n");
		exit(EXIT_FAILURE);
	}
	printf("Socket bound.\n");


	//Socket listens
	if ((listen(socketfd, 1)) != 0) {
		printf("Listening failed.\n");
		exit(EXIT_FAILURE);
	}
	printf("Server listening.\n");


	//Accept package from client
	struct sockaddr_in clientSockAddr;
	int lenClSoAddr = sizeof(clientSockAddr);
	int connectionfd = accept(socketfd, (struct sockaddr*) &clientSockAddr,
			&lenClSoAddr);
	if (connectionfd < 0) {
		printf("Accept from Server failed.\n");
		exit(EXIT_FAILURE);
	}
	printf("Client connection accepted.\n");


	//As there now exists a connection between client and server,
	//they may exchange data
	exchangeSomeData(connectionfd);

	//Closing the socket
	close(socketfd);

	exit(EXIT_SUCCESS);
}
