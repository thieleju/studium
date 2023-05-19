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

void exchangeSomeData(int connectionfd)
{
	char readBuffer[bufferSize];
	while (1)
	{
		// Eingabe der Primzahlposition
		printf("Geben Sie die Position der Primzahl ein (exit zum Beenden): ");
		fgets(readBuffer, sizeof(readBuffer), stdin);

		// Übertragung beenden, wenn "exit" eingegeben wird
		readBuffer[strcspn(readBuffer, "\n")] = '\0'; // Newline-Zeichen entfernen
		if (strcmp(readBuffer, "exit") == 0)
			break;

		// Primzahlposition an den Server senden
		send(connectionfd, readBuffer, sizeof(readBuffer), 0);

		// Primzahl vom Server empfangen
		memset(readBuffer, 0, sizeof(readBuffer));
		recv(connectionfd, readBuffer, sizeof(readBuffer), 0);

		// Ausgabe der empfangenen Primzahl
		printf("Empfangene Primzahl: %s\n", readBuffer);
	}
}

int main()
{
	// create a socket
	int connectionfd = socket(AF_INET, SOCK_STREAM, 0);
	if (connectionfd == -1)
	{
		printf("Fehler beim Erzeugen des Sockets.\n");
		exit(EXIT_FAILURE);
	}
	printf("Socket erstellt.\n");

	// set server IP and port
	struct sockaddr_in serverSockAddr;
	serverSockAddr.sin_family = AF_INET;
	serverSockAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverSockAddr.sin_port = htons(port);

	// Connect client and server sockets
	if (connect(connectionfd, (struct sockaddr *)&serverSockAddr, sizeof(serverSockAddr)) != 0)
	{
		printf("Verbindung zum Server fehlgeschlagen.\n");
		exit(EXIT_FAILURE);
	}
	printf("Verbindung zum Server hergestellt.\n");

	// function for chat
	exchangeSomeData(connectionfd);

	// close the socket
	close(connectionfd);

	exit(EXIT_SUCCESS);
}
