# Übung 9 

Im vorigen Übungsblatt haben wir eine Parallelverarbeitung basierend auf mehreren Prozessinstanzen umzusetzen. Ein oftmals besser geeignetes Konzept sind Threads, die anders als Prozesse im gemeinsamen Prozessspeicher laufen. Sie sind hierdurch leichtgewichtiger als Prozesse, andererseits müssen Vorkehrungen getroffen werden, damit durch den nebenläufigen Zugriff auf den gemeinsamen Speicherbereich keine ungewünschten Seiteneffekte entstehen. Wir verwenden in dieser Übung POSIX-konforme pthreads.


## Aufgabe 1

Ein pthread erhält ähnlich einer PID eines Prozesses einen Identifier vom Typ pthread_t. Ein Thread muss einer Funktion zugeordnet werden. Diese Funktion muss eine Rückgabe vom Typ void* sowie einen einzigen Parameter vom Typ void* besitzen. Mehrere Threads können an die gleiche Funktion gebunden werden, um diese von den Threads nebenläufig mehrere Male ausführen zu lassen. Erstellen Sie ein Programm, das mittels pthread_create(...) zwei Threads erzeugt, die an eine von Ihnen zu schreibende Funktion doWork(...) gebunden werden sollen. doWork(...) soll einen Integer entgegennehmen und auf der Konsole ausgeben. 

Hinweis: Bedingt durch die von phtreads erzwungene Signatur von an Threads gebundenen Funktionen, können Sie an doWork(...) nicht direkt einen Integer übergeben.


## Aufgabe 2

Da die doWork(...) einen Pointer als Argument erwartet, haben Sie womöglich einen zu void*- gecasteten Zeiger auf Ihren Integer übergeben. Ändern Sie die main(...) nun so ab, dass Sie in einer for-Schleife 50 Threads erzeugen. Übergeben Sie den aktuellen Wert des Schleifenzählers an die doWork(...)-Funktion und geben Sie ihn auf der Konsole aus. Was fällt Ihnen auf? Was kann die Ursache hierfür sein? 

Hinweis: Eine übliche, aber unsaubere Möglichkeit, Werte an in Threads laufende Funktionen zu übergeben ist, sie schlicht als void* zu casten. So wird aus einem int ein void*, der in der Funktion des Threads wieder zurück zu einem int gecastet wird. Dies funktioniert meistens, setzt aber voraus, dass Pointer, d.h. Adressvariablen und der eigentliche Variablentyp die gleiche Länge haben. Im Regelfall muss man stattdessen durch die Gestaltung des Codes sicherstellen, dass sich die über die Adresse an die Funktion übergebene Variable bis zum Start des Threads nicht verändert.

- Die Zahlen in der Ausgabe sind nicht korrekt
- Es fehlen viele der 50 Zahlen
- Programm wird beendet, bevor alle Threads fertig sind

## Aufgabe 3

Binden Sie nun die primeArray.h der vorigen Übung ein, erzeugen Sie in der main() mittels setupArray() das Array aus großen Zahlen und faktorisieren Sie die enthaltenen 128 Zahlen nun in 128 Threads. Speichern Sie auch hier die Primfaktoren in der zweiten bzw. dritten Spalte des Arrays. Messen Sie die Laufzeit Ihres Programms und vergleichen Sie sie mit der Lösung der vorigen Übung, die Prozesse eingesetzt hat. Denken Sie daran, mit pthread_join(...) auf die Beendigung der Threads zu warten, bevor Sie ihren Prozess beenden. 

Hinweis: Sind Sie sicher, dass Sie nicht den Fehler begangen haben, den Sie in Aufgabe 2 beobachtet haben?

## Aufgabe 4

Stellt die Aufteilung des Problems in 128 Threads eine effiziente Lösung dar? Warum bzw. warum nicht? Wie sähe eine geschicktere Aufteilung aus und wie könnte man diese realisieren?

Hinweis: Die Umsetzung in Code ist recht anspruchsvoll und nicht notwendig.

- Die Anzahl der Threads sollte in der Regel in einem vernünftigen Verhältnis zur Anzahl der verfügbaren CPU-Kerne stehen. 
- Wenn die Anzahl der Threads viel größer ist als die Anzahl der Kerne, kann dies zu Overhead und Wettbewerb um Ressourcen führen, was die Gesamtleistung beeinträchtigen kann.