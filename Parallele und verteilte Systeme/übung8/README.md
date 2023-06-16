# Übung 8 

In den vergangenen Übungsblättern sowie in den Vorlesungen haben wir verschiedene Verfahren kennengelernt, mittels derer Prozesse miteinander kommunizieren können. Hierbei können die Prozesse auch auf unterschiedlichen Rechnern laufen. Eine Interprozesskommunikation ermöglicht nun das verteilte Rechnen. Eine Ausprägung hiervon ist die Parallelisierung: Eine Problemstellung wird auf mehrere lokale oder verteilte Prozesse verteilt, um durch die in Summe höhere Rechenleistung in kürzerer Zeit fertiggestellt zu werden. In der Vorlesung werden wir noch auf die Grenzen der theoretisch und praktisch erreichbaren Beschleunigung eingehen. 

In diesem Übungsblatt werden wir die Parallelisierung für lokal laufende Prozesse umsetzen, die mittels Pipes kommunizieren. Durch einen Wechsel auf ein andere in der Vorlesung behandelte Kommunikationsparadigma kann die Parallelisierung auch analog auf verteilten Rechnern umgesetzt werden.

## Aufgabe 1

Schreiben Sie ein Programm, das aus einem Elternprozess und via fork() abgeleiteten Kindsprozessen bestehen soll. Der Elternprozess soll die Koordination der parallel ablaufenden Arbeiten übernehmen, die Kindsprozesse die eigentliche parallele Verarbeitung betreiben. Schreiben Sie das Programm derart, dass Sie mittels #define gewünschte Anzahl an Kindsprozessen vorgeben können und richten Sie entsprechend für jeden Kindsprozess je eine Pipe zum Lesen und zum Schreiben vom bzw. zum Elternprozess ein. Schreiben Sie das Programm so, dass nur der Elternprozess per fork() verzweigt. 

Übertragen Sie zum Testen Werte an jeden Kindsprozess, leiten Sie sie zurück zum Elternprozess und geben Sie sie auf der Konsole aus. 

Hinweis: Sie müssen im Elternprozess eine Liste der Prozess-IDs der Kindsprozesse vorhalten, um jedem Prozess eine jeweils eindeutige Pipe zum Lesen bzw. zum Schreiben zuordnen zu können. Denken Sie zudem daran, im jeweiligen Prozess die nicht genutzten Enden der Pipes zu Beginn sowie die genutzten Enden nach Durchlauf zu schließen. 

## Aufgabe 2

Was geschieht bei einem Aufruf von fork()? 

- Beim Aufruf von fork() wird ein neuer Prozess erzeugt, der eine Kopie des aktuellen Prozesses ist.
- Der neue Prozess (Kindprozess) erhält eine eigene Kopie des Speichers, des Dateideskriptors und anderer Ressourcen des Elternprozesses.
- Der Kindprozess setzt seine Ausführung an der Stelle des fork()-Aufrufs fort und hat eine eindeutige Prozess-ID (PID).
- Im Elternprozess wird die PID des Kindprozesses von fork() zurückgegeben, im Kindprozess wird 0 zurückgegeben.

Welches alternative Konzept anstelle mehrerer unabhängiger Prozesse könnte sich für eine lokale Parallelisierung ggf. besser eignen und weshalb?

- Threads: Leichtgewichtige Prozesse, die innerhalb desselben Prozesses arbeiten. Teilen sich gleichen Speicherbereich und Ressourcen des Elternprozesses. Kommunikation und Synchronisation zwischen Threads ist einfacher und schneller als zwischen Prozessen.
- Parallelisierung mit Multi Core: Moderne Prozessoren haben mehrere Kerne, die gleichzeitig arbeiten können.
- Gleichzeitige Ausführung (Concurrency): Hierbei werden verschiedene Aufgaben gleichzeitig ausgeführt, aber nicht notwendigerweise parallel.

## Aufgabe 3

Erstellen Sie vier Kindsprozesse. Fügen Sie für den zweiten Ihrer Kindsprozesse nun mittels sleep(...) eine Wartezeit von einigen Sekunden zwischen dem Empfangen und Zurücksenden des Wertes ein. Was beobachten Sie auf der Konsolenausgabe? Überlegen Sie, in welchem Zusammenhang und warum das beobachtete Verhalten bei einer Parallelverarbeitung zu einem deutlichen Leistungseinbruch führt. 

Hinweis: Ausgaben auf der Konsole werden für Sie unsichtbar gepuffert. Hierdurch kann die Reihenfolge der Konsolenausgaben scheinbar widersprüchlich sein. Fügen Sie nach jedem printf(...)-Befehl die Instruktion fflush(stdout) hinzu, um die Ausgabepuffer zu leeren. 

## Aufgabe 4

Abhängig von Ihrer Umsetzung kann die in Aufgabe 3 hinzugefügte Verzögerung auftreten bevor oder nachdem alle Kindsprozesse mit Daten versorgt wurden. Welche Variante ist für eine Parallelverarbeitung kritischer? 

- Die Verzögerung ist für die Parallelverarbeitung kritischer, wenn sie vor dem Versorgen aller Kindsprozesse mit Daten auftritt. Wenn die Verzögerung auftritt, bevor alle Kindsprozesse ihre Daten gesendet haben, kann es zu einem Engpass kommen, da die anderen Kindsprozesse auf die Antwort des verzögerten Kindsprozesses warten müssen, bevor sie ihre eigenen Daten senden können.

Ändern Sie Ihre Implementierung so ab, dass die Verzögerung in ihrer weniger kritischen Variante auftritt. 

## Aufgabe 5

Wir testen nun den Leistungszugewinn durch Parallelisierung. Wir simulieren einen Brute Force- Angriff, um eine verschlüsselte Kommunikation zu brechen. Hierzu müssen wir aus einer Reihe sehr  großer Ganzzahlen die zwei jeweiligen Primfaktoren identifizieren. Schreiben Sie eine Funktion factors(...), die eine aus zwei Primfaktoren gebildete Ganzzahl entgegennimmt und mittels zwei Pointern die gefundenen Faktoren zurückgibt. Binden Sie dann den Header primeArray.h in Ihr Projekt ein, der 128 sehr große solcher Ganzzahlen enthält und rufen Sie in Ihrer main() die Funktion setpArray() auf. Das Array ist so aufgebaut, dass in der zweiten und dritten Spalte jeder Zeile die jeweiligen Faktoren abgespeichert werden sollen. Lassen Sie sich die Laufzeit Ihres Programms ausgeben, wenn Sie es zunächst nur in einem einzelnen Prozess ausführen, der alle 128 Zahlen aus primeArray faktorisiert. Legen Sie dann Varianten an mit 2, 4, 8, ... Kindsprozessen, die sich die Aufgabe teilen und die gefundenen Faktoren per Pipes an den Elternprozess zurückgeben und messen Sie dann die Gesamtzeit, die Ihr Programm benötigt. Die Hilfsfunktion printFactorList(...) gibt Ihnen übersichtlich alle Zahlen des Arrays sowie die von Ihnen hinterlegten Faktoren aus. 

Hinweise: Code zum Messen der Laufzeit können Sie aus einer der vorigen Übungen kopieren. Um diese großen Zahlen zu verarbeiten, sollten Sie statt int den Datentyp unsigned long long verwenden. Da sich jede Ganzzahl als Produkt aus Potenzen von Primzahlen darstellen lässt und die beiden Faktoren der großen Zahl ohnehin prim sind, könnten Sie zur Optimierung nur Primzahlen statt ganzer Zahlen durchprobieren. Denken Sie daran, dass Sie Ihren Kindsprozessen irgendwie mitteilen müssen, welche Zahl vom jeweiligen Prozess nun zu faktorisieren ist. Wo ist der Unterschied, wenn Sie den Kindsprozessen die zu faktorisierenden Zahlen oder nur den Index des PrimeArrays übergeben? 


Ergebnisse mit optimiertem Algorithmus + usage struct
```bash
1 Prozess: Durchschnittliche Zeit pro Prozess: 111.834715 Sekunden
2 Prozesse: Durchschnittliche Zeit pro Prozess: 57.993755 Sekunden
4 Prozesse: Durchschnittliche Zeit pro Prozess: 34.542926 Sekunden
8 Prozesse: Durchschnittliche Zeit pro Prozess: 17.226741 Sekunden 
16 Prozesse: Durchschnittliche Zeit pro Prozess: 8.600457 Sekunden
```

Da mein Prozessor 4 Kerne hat, ist 35 Sekunden bei 4 Prozessen der schnellste Weg die Zahlen zu faktorisieren.