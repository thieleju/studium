#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

bool is_prime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

void generate_primes(int n) {
    int count = 2; // Starting count from 2 because 2 and 3 are already in the array
    int largest_prime = 3;
    int* primes = malloc(n * sizeof(int)); // Allocate memory for primes array
    primes[0] = 2;
    primes[1] = 3;

    for (int i = 5; count < n; i += 2) { // Iterate over odd numbers starting from 5
        if (is_prime(i)) {
            bool is_divisible = false;
            for (int j = 0; j < count; j++) {
                if (i % primes[j] == 0) {
                    is_divisible = true;
                    break;
                }
            }
            if (!is_divisible) {
                primes[count] = i;
                count++;
                largest_prime = i;
            }
        }
    }
    printf("Largest prime generated: %d\n", largest_prime);
    free(primes); // Free the allocated memory for primes array
}

int main() {
    pid_t pid = fork();
    if (pid < 0) {
        fprintf(stderr, "Fork failed.\n");
        return 1;
    } else if (pid == 0) {
        // First child process
        generate_primes(500); // Generate 500 primes
        return 0;
    } else {
        // Parent process
        pid_t pid2 = fork();
        if (pid2 < 0) {
            fprintf(stderr, "Fork failed.\n");
            return 1;
        } else if (pid2 == 0) {
            // Second child process
            generate_primes(1000); // Generate 1000 primes
            return 0;
        } else {
            // Parent process
            wait(NULL); // Wait for first child to finish
            wait(NULL); // Wait for second child to finish
            printf("Child processes finished.\n");
            return 0;
        }
    }
}
