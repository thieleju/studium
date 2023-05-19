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

void exchangeSomeData(int connectionfd) {
	char readBuffer[bufferSize];
	while (1) {
		//send data to server
		printf("Type line to be sent to server:");
		size_t len;
		char *line = NULL;
		ssize_t getLineret = getline(&line, &len, stdin);
		write(connectionfd, line, getLineret-1);
		free(line);

		//receive data from server
		bzero(readBuffer, bufferSize);
		read(connectionfd, readBuffer, bufferSize);
		printf("Received from Server : %s\n", readBuffer);
		fflush(stdout);

		//end transmission, if "exit" is read
		if (!strncmp("exit", readBuffer, 4)) {
			printf("Ending Transmission.\n");
			break;
		}
	}
}

int main() {
	//create a socket
	int connectionfd;


	//set server IP and port



	//Connect client and server sockets




	// function for chat
	exchangeSomeData(connectionfd);

	// close the socket


	exit(EXIT_SUCCESS);
}
