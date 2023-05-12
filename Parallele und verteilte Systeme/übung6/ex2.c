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
    int count = 0;
    int largest_prime = 0;
    for (int i = 2; count < n; i++) {
        if (is_prime(i)) {
            count++;
            largest_prime = i;
        }
    }
    printf("Largest prime generated: %d\n", largest_prime);
}

int main() {
    pid_t pid = fork();
    if (pid < 0) {
        fprintf(stderr, "Fork failed.\n");
        return 1;
    } else if (pid == 0) {
        // Child process
        generate_primes(1000); // Generate 1000 primes
        return 0;
    } else {
        // Parent process
        wait(NULL); // Wait for child to finish
        printf("Child process finished.\n");
        return 0;
    }
}
