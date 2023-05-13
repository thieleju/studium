# Übung 6

Oftmals laufen Prozesse nicht als isolierte Anwendungen, sondern müssen mit anderen Prozessen
kommunizieren. Für diese sog. Interprozesskommunikation gibt es verschiedene Mechanismen wie
bspw. Pipes, Shared Memory oder Sockets. Diese unterscheiden sich unter anderem hinsichtlich ihrer
Direktionalität, ihres Zugriffsverhaltens, der Anzahl der Teilnehmer oder der Tatsache, ob die
Kommunikation zwischen einem Elternprozess und seinen Abkömmlingen oder unabhängig
voneinander erzeugten Prozessen stattfindet.
In dieser Übung behandeln wir Pipes, unidirektionale Verbindungen zwischen Prozessen, die mittels
fork() entstanden sind.

# Aufgabe 1
In der Vorlesung haben wir eine Optimierung der Primzahlengenerierung aus dem vorigen
Übungsblatt behandelt. Wir möchten nun Ihr Verfahren, das Sie letzte Woche implementiert haben
dem optimierten Verfahren gegenüberstellen. Erstellen Sie ein Programm, das sich in drei Prozesse
aufteilt. Hierzu wird er Elternprozess mittels fork() geteilt, der entstehende Kindsprozess wird ein
weiteres Mal mit fork() geteilt. Überprüfen Sie jeweils, ob das Aufteilen der Prozesse erfolgreich war.

# Aufgabe 2
Setzen Sie in einem der Kindsprozesse Ihr Programm zur Primzahlerzeugung von letzter Woche um.
Schreiben Sie hierfür eine Funktion, die von Ihrem Kindsprozess aufgerufen wird. Als Argument
möchten Sie die Anzahl der zu generierenden Primzahlen übergeben können. Lassen Sie die
Funktion die auf diese Art größte erzeugte Primzahl auf der Konsole ausgeben.

# Aufgabe 3
Setzen Sie nun in dem anderen Kindsprozess das Verfahren zur Primzahlerzeugung um, das wir in
der Vorlesung behandelt haben. Auch hier möchten wir eine Funktion anlegen, die als Argument, die
Anzahl der zu erzeugenden Primzahlen erhält. Lassen Sie die Funktion die auf diese Art erzeugte
größte Primzahl auf der Konsole ausgeben.
Beschreibung der Vorgehensweise:
 Legen Sie mittels malloc() auf dem Heap ein Array für Integer an, in dem die erzeugten
Primzahlen abgelegt werden sollen. Die ersten beiden Einträge können manuell mit den
ersten beiden Primzahlen 2 und 3 gefüllt werden.
 Solange Sie noch nicht die übergebene Anzahl an Primzahlen generiert haben, überprüfen
Sie in Folge für alle ungeraden Zahlen, ob sie sich durch die bereits gefundenen Primzahlen,
die im Array abgelegt sind, teilen lassen.
 Haben Sie auf diese Weise eine neue Primzahl gefunden, so legen Sie sie im Array ab.
Hinweis: Denken Sie nach Durchlauf der Funktion daran, allokierten Speiche wieder mittels free()
freizugeben.

# Aufgabe 4
Wir möchten nun, dass Sie im Elternprozess in einer Variable primNum die Anzahl der zu
generierenden Primzahlen eintragen können. Dieser Wert soll mittels Pipes an die Kindprozesse
gesendet werden. Diese sollen dann die entsprechende Anzahl an Primzahlen generieren. Erzeugen
Sie daher oberhalb des ersten fork() in Ihrem Programm zwei Integer-Arrays der Länge 2 namens
pipeP1 und pipeP2 und daraus mittels Aufruf der Funktion pipe(…) jeweils eine Pipe.
Schließen Sie im Elternprozess für jede Pipe das Leseende pipeP1[0] und pipeP2[0] mittels
close(…). In den Kindsprozessen schließen Sie zu Beginn die Schreibenden pipeP1[1] bzw.
pipeP2[1]. Legen Sie dann in jedem Kindprozess einen Integer x an. Übertragen Sie den Wert von
primNum mittels write(…) vom Elternprozess an die Kindprozesse. Diese lesen den Wert in die
Variable x mittels read(…) ein und rufen damit ihre Funktion zur Primzahlengenerierung auf. Vor
Aufruf von exit(…) müssen dann noch die verwendeten Enden der Pipe im entsprechenden Prozess
geschlossen werden.
Hinweise: Achten Sie darauf, zu überprüfen, ob die jeweiligen Funktionsaufrufe erfolgreich
abgeschlossen wurden. Falls nicht, reagieren Sie entsprechend. Vergessen Sie nicht, im
Elternprozess auf die Beendigung aller Kindprozesse zu warten. Damit Konsolenausgaben
unmittelbar erscheinen, können Sie mittels fflush(stdout) den Ausgabepuffer direkt nach jedem
printf(…) leeren.

# Aufgabe 5
Versuchen Sie einmal, in einem Kindprozess ein zweites Mal mittels read(…) zu lesen. Woran können
Sie erkennen, dass Sie beim zweiten Mal nicht erfolgreich gelesen haben?
- Die read() Funktion blockiert und wartet auf neue Daten, die in die Pipe geschrieben werden.

# Aufgabe 6
Fügen Sie nun Code hinzu, der in jedem der Kindprozesse die Laufzeit misst. Senden Sie dann diese
Zeitinformation über eine jeweils weitere Pipe an den Elternprozess zurück. Der Elternprozess soll die
Laufzeiten beider Verfahren dann auf der Konsole ausgeben. Testen Sie beide Algorithmen, indem
Sie jeweils 10.000 Primzahlen generieren lassen.
Hinweis: Bedienen Sie sich hierfür aus Code aus 

