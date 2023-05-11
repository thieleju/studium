#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>
#include <lmcons.h>

int main()
{
  TCHAR username[UNLEN + 1];
  DWORD username_len = UNLEN + 1;
  GetUserName(username, &username_len);
  printf("Hello user %s\n", username);

  TCHAR current_dir[MAX_PATH];
  GetCurrentDirectory(MAX_PATH, current_dir);
  printf("You are executing this in directory %s\n", current_dir);

  LARGE_INTEGER frequency, start_time, end_time;
  QueryPerformanceFrequency(&frequency);
  QueryPerformanceCounter(&start_time);

  for (unsigned long i = 0; i < 10000; i++)
  {
    printf("I'm just here to consume some time ... i is currently %lu\n", i);
  }

  QueryPerformanceCounter(&end_time);
  double elapsed_seconds = (double)(end_time.QuadPart - start_time.QuadPart) / frequency.QuadPart;
  printf("CPU time spent: %lf\n", elapsed_seconds);
}
