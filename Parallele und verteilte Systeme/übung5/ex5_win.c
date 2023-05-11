#include <windows.h>
#include <stdio.h>
#include <psapi.h>

void printProcessInfo(HANDLE hProcess)
{
  HMODULE hMod;
  DWORD cbNeeded;
  char processType[MAX_PATH];

  // get process ID
  DWORD processId = GetProcessId(hProcess);
  printf("Process ID: %lu\n", processId);

  // get executable name
  if (EnumProcessModules(hProcess, &hMod, sizeof(hMod), &cbNeeded))
  {
    GetModuleBaseNameA(hProcess, hMod, processType, MAX_PATH);
    printf("Executable name: %s\n", processType);
  }
}

int main()
{
  STARTUPINFO si;
  PROCESS_INFORMATION pi;
  HANDLE childHandle;

  ZeroMemory(&si, sizeof(si));
  si.cb = sizeof(si);
  ZeroMemory(&pi, sizeof(pi));

  // create child process
  if (CreateProcess(NULL, "notepad.exe", NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi))
  {
    printf("Child process created.\n");

    // print child process info
    printProcessInfo(pi.hProcess);

    // wait for child process to finish
    WaitForSingleObject(pi.hProcess, INFINITE);

    // close process and thread handles
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);
  }
  else
  {
    printf("Failed to create child process.\n");
    return 1;
  }

  return 0;
}
