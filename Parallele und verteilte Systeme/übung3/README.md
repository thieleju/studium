# Aufgabe 1

Lesen Sie das folgende Programm und versuchen Sie, die folgenden Fragen zu beantworten ohne das Programm auszuführen. Malen Sie sich eine Speicherskizze auf, um die Fragen zu beantworten.
```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    int x = 5;
    int* myPointer = &x;
    printf( --- siehe Fragen unten ---);
    return 0;
}
```

Was bewirkt die Ausgabe printf("%d.\n", x);?
- Gibt den Wert von x aus
- Ausgabe: 5

Was bewirkt die Ausgabe printf("%d.\n", myPointer);?
- Gibt die Adresse von x aus (aber in dezimaler Form, da %d anstatt %p) 
- Ausgabe: 27260604 (in hex: 19FF6BC)

Was bewirkt die Ausgabe printf("%p.\n", &x);?
- Gibt die Adresse von x aus
- Ausgabe: 000000bd019ff6bc 

Was bewirkt die Ausgabe printf("%p.\n", &myPointer);?
- Gibt die Adresse von myPointer aus
- Ausgabe: 000000bd019ff6b0

Was bewirkt die Ausgabe printf("%d.\n", *myPointer);?
- Gibt den Wert von x aus
- Ausgabe: 5

Was passiert beim Versuch, folgendes auszugeben: printf("%d.\n", *x);?
- Fehler, da x kein Pointer ist 
- "operand of '*' must be a pointer but has type 'int'"

# Aufgabe 2

Sie fügen in dem Programm aus Aufgabe 1 vor dem Printf-Befehl folgende Zeile hinzu:
```c
*myPointer = 10;
```
Beantworten Sie folgende Fragen ohne das Programm auszuführen:

Was bewirkt die Ausgabe printf("%d.\n", x);?
- Gibt den Wert von x aus
- Ausgabe: 10

Was bewirkt die Ausgabe printf("%d.\n", *myPointer);?
- Gibt den Wert von x aus
- Ausgabe: 10

# Aufgabe 3

Sie können auch Pointer als Parameter für Funktionen deklarieren. Erinnern Sie sich, wie in der letzten Übung das Vertauschen zweier Variablen mittels `vertausche(int x, int y)` nicht funktioniert hatte. Schreiben Sie eine Funktion `vertauscheMitPointern()`, die tatsächlich die übergebenen Variablen vertauscht. Überlegen Sie, von welchem Typ die Parameter dieser Funktion sind.

# Aufgabe 4

Wenn Sie Aufgabe 3 richtig bearbeitet haben, werden die Variablen x und y aus main() in der Funktion vertauscheMitPointern() tatsächlich manipuliert. Das ändert nichts an der Tatsache, dass es sich noch immer um einen Call-by-Value handelt. 

Von welchen Variablen werden für die Funktion vertauscheMitPointern() lokale Kopien erzeugt? 

Weshalb stellt das kein Problem dar? 

Verdeutlichen Sie Ihre Lösung mit einer Speicherskizze.

