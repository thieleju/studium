#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void generate_primes(int start, int end, char* label) {
    // Generate prime numbers in the range [start, end] and print them
    int is_prime;
    for (int n = start; n <= end; n++) {
        is_prime = 1;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                is_prime = 0;
                break;
            }
        }
        if (is_prime) {
            printf("%s: Prime %d\n", label, n);
        }
    }
}

int main() {
    int pid1, pid2;
    int status;

    // Fork the first child process
    pid1 = fork();
    if (pid1 == -1) {
        printf("Error: fork() failed\n");
        exit(1);
    } else if (pid1 == 0) {
        // This is the first child process
        printf("First child process (pid=%d) created\n", getpid());
        generate_primes(2, 1000, "First child");
        exit(0);
    }

    // Fork the second child process
    pid2 = fork();
    if (pid2 == -1) {
        printf("Error: fork() failed\n");
        exit(1);
    } else if (pid2 == 0) {
        // This is the second child process
        printf("Second child process (pid=%d) created\n", getpid());
        generate_primes(1001, 2000, "Second child");
        exit(0);
    }

    // Wait for both child processes to finish
    waitpid(pid1, &status, 0);
    printf("First child process (pid=%d) terminated with status %d\n", pid1, status);
    waitpid(pid2, &status, 0);
    printf("Second child process (pid=%d) terminated with status %d\n", pid2, status);

    return 0;
}
