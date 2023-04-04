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

Was bewirkt die Ausgabe printf("%d.\n", \*myPointer);?

- Gibt den Wert von x aus
- Ausgabe: 5

Was passiert beim Versuch, folgendes auszugeben: printf("%d.\n", \*x);?

- Fehler, da x kein Pointer ist
- "operand of '\*' must be a pointer but has type 'int'"

# Aufgabe 2

Sie fügen in dem Programm aus Aufgabe 1 vor dem Printf-Befehl folgende Zeile hinzu:

```c
*myPointer = 10;
```

Beantworten Sie folgende Fragen ohne das Programm auszuführen:

Was bewirkt die Ausgabe printf("%d.\n", x);?

- Gibt den Wert von x aus
- Ausgabe: 10

Was bewirkt die Ausgabe printf("%d.\n", \*myPointer);?

- Gibt den Wert von x aus
- Ausgabe: 10

# Aufgabe 3

Sie können auch Pointer als Parameter für Funktionen deklarieren. Erinnern Sie sich, wie in der letzten Übung das Vertauschen zweier Variablen mittels `vertausche(int x, int y)` nicht funktioniert hatte. Schreiben Sie eine Funktion `vertauscheMitPointern()`, die tatsächlich die übergebenen Variablen vertauscht. Überlegen Sie, von welchem Typ die Parameter dieser Funktion sind.

# Aufgabe 4

Wenn Sie Aufgabe 3 richtig bearbeitet haben, werden die Variablen x und y aus main() in der Funktion vertauscheMitPointern() tatsächlich manipuliert. Das ändert nichts an der Tatsache, dass es sich noch immer um einen Call-by-Value handelt.

Von welchen Variablen werden für die Funktion vertauscheMitPointern() lokale Kopien erzeugt?

- Die Funktion bekommt die Adressen an Pointer übergeben. Diese Pointer tauschen danach die Werte.
- Es werden keine Kopien von lokalen Variablen erzeugt?

Weshalb stellt das kein Problem dar?

- Die lokale Kopie der Pointer wird nicht verändert, sondern nur die Werte, auf die die Pointer zeigen.

Verdeutlichen Sie Ihre Lösung mit einer Speicherskizze.

heap: dynamischer Speicherplatz zur Datenablage<br>
stack: loakle Variablen und Funktionsaufrufe<br>
static: statische/globale Variablen

| Typ   | Variable | Adresse | Wert  |
| ----- | -------- | ------- | ----- |
| stack | y        | 0xffe   | 5     |
| stack | x        | 0xfff   | 10    |
| stack | py       | 0xffc   | 0xfff |
| stack | px       | 0xffd   | 0xffe |
| ----- | -------- | ------- | ----- |
| stack | temp     | 0xffb   | 0xfff |
| stack | py       | 0xffc   | 0xffe |
| stack | px       | 0xffd   | 0xfff |
| stack | y        | 0xffe   | 10    |
| stack | x        | 0xfff   | 5     |

# Aufgabe 5

Reservieren Sie mit malloc() einen Speicherbereich für 256 Integer-Variablen. Überprüfen Sie, ob die Zuweisung erfolgreich war und befüllen Sie den Bereich mit aufsteigenden Werten von 0 bis 255.

```c
int *array = (int*)malloc(256 * sizeof(int));
if (array == NULL)
{
    printf("Fehlgeschlagen!\n");
    return 1;
}
for (int i = 0; i < 256; i++)
{
    array[i] = i;
}
free(array);
```

# Aufgabe 6

Woran erkennen Sie, ob eine Speicheranforderung mittels malloc() erfolgreich war oder nicht?

- Wenn der Rückgabewert NULL ist, dann ist die Anforderung fehlgeschlagen.

Was können geeignete Reaktionen sein, wenn die Allokation nicht erfolgreich war?

- Programmabbruch (return 1)

# Aufgabe 7

Reservieren Sie im gleichen Programm von Aufgabe 1 nun mit malloc() 1MiB Speicher. Dieser Bereich soll für Variablen vom Typ double genutzt werden können.
Wie viele Variablen vom Typ double finden in dem Speicherbereich Platz?

- sizeof(double) = 8 Byte
- 1 MiB (1024 \* 1024 Byte) / 8 Byte = 131072 Variablen vom Typ double

Was geschieht, wenn Sie nun einen Integer in diesen Speicherbereich schreiben möchten?

- Die Integer Variable wird in die 8 Byte geschrieben, die restlichen 4 Byte bleiben unverändert.
- Sollte vermieden werden, da es zu Fehlern führen kann.

Hinweise: Machen Sie sich den Unterschied zwischen MiB und MB noch einmal bewusst. Vergessen Sie nicht, den Pointer, den malloc() zurückliefert passend zu casten.

# Aufgabe 8

Schreiben Sie eine while(1)-Schleife, in der Sie in jedem Durchlauf einen neuen Speicherbereich für Integer von 100 MiB reservieren, den Sie in jedem Durchgang mit von 0 aufsteigenden Zahlen befüllen.

Wie viele Integer passen in einen 1 MiB großen Speicherbereich?

- 1 MiB (1024 \* 1024 Byte) / 4 Byte = 262144 Integer

Zählen Sie die Schleifendurchgänge in Ihrem Programm, um festzustellen, wie viel Speicher Sie insgesamt reservieren konnten.

- Ergebnis: 56 GiB (581 Durchgänge)
- Falsches Ergebnis?

# Aufgabe 9

Haben Sie daran gedacht, alle reservierten Speicherbereiche nach Benutzung wieder mit free(...) freizugeben und den korrespondierenden Zeiger als ungültig zu markieren?

- Dann läuft eine Endlosschleife?

# Aufgabe 10

Skizzieren Sie eine Situation, bei der das Vergessen der Speicherfreigabe mittels free() zu Problemen führt.

- Ein Speicherleck kann auch dazu führen, dass das Programm langsam wird, da es immer mehr Speicherbereiche allozieren muss, um die Anforderungen des Programms zu erfüllen. Wenn das Programm in einer Endlosschleife läuft, kann es sogar dazu führen, dass das System nicht mehr reagiert oder abstürzt.
- Ein weiteres Problem ist, dass ein Speicherleck dazu führen kann, dass vertrauliche Daten im Speicher verbleiben. Wenn ein Programm beispielsweise Passwörter oder andere vertrauliche Informationen im Speicher speichert und das Programm beendet wird, ohne dass der Speicher freigegeben wird, können diese Informationen potenziell von anderen Programmen oder Benutzern gelesen werden, die denselben Speicherbereich nutzen.

# Aufgabe 11

Weshalb ist es sinnvoll, den Zeiger, der auf den reservierten Speicherbereich zeigt, nach Freigabe dieses Speicherbereichs auf NULL zu setzen?

- Wenn der Zeiger nicht auf NULL gesetzt wird, kann es zu Fehlern kommen, wenn der Speicherbereich erneut alloziert wird.

# Aufgabe 12

Was passiert, wenn Sie einen allokierten Bereich zweimal mittels free() freigeben?

- Es tritt ein Fehler auf und das Programm beendet sich

# Aufgabe 13

Angenommen, der Zeiger int* myMemory zeigt auf einen von Ihnen mittels malloc() allokierten Speicherbereich. Nun führen Sie folgende Zeile aus:
`int* myOtherMemory = myMemory;` Was wurde nun kopiert?

- Der Zeiger myOtherMemory zeigt nun auf den gleichen Speicherbereich wie myMemory.

Wie oft muss der allokierte Speicher nun mittels free() freigegeben werden?

- Nur einmal, da der Speicherbereich nur einmal alloziert wurde.

# Aufgabe 14

Zeichnen Sie eine Speicherskizze, um das obige Verhalten nachzuvollziehen. Revidieren Sie ggf. Ihre Antwort.

# Aufgabe 15

Was könnten Ihrer Meinung nach Richtlinien sein, wie man Fehler in der dynamischen Speicherallokation vermeiden könnte? Wer sollte die Verantwortung tragen, dass allokierter Speicher wieder mit free() freigegeben wird?

- Beim allozieren von Speicher sollte immer überprüft werden, ob die Anforderung erfolgreich war. (!= NULL)
- Der Speicher sollte immer wieder freigegeben werden, wenn er nicht mehr benötigt wird. (free())
- Der Programmierer sollte die Verantwortung tragen, dass der Speicher wieder freigegeben wird.
