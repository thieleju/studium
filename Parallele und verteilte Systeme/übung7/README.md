# Übung 7

## Aufgabe 1
Sie erhalten für diese Übung Code für einen TCP-Server und Code für einen TCP-Client. Der Code für den Server ist vorerst vollständig und muss von Ihnen nur noch kompiliert werden. Der Code für den TCP-Client lässt sich zwar auch kompilieren, ihm fehlt jedoch noch der relevante Teil zum Aufbau eines Sockets. Kompilieren Sie beide Dateien als jeweils eigenes Projekt und führen Sie sie beide (bspw. in zwei Kommandozeilenfenstern) aus.

## Aufgabe 2
Der Ablauf für einen TCP-Socket auf Serverseite sieht die folgenden Schritte vor:
1. Socket erzeugen
2. Socket mit Adress-Interface verbinden
3. Auf Clients lauschen
4. Client-Verbindungsanfrage akzeptieren
5. Kommunizieren
6. Socket schließen

Sehen Sie sich den Code des TCP-Servers an und beantworten Sie die folgenden Fragen:

**1. Ein Socket wird mit dem Befehl socket(...) erstellt. Was bedeutet AF_INET?**
- `AF_INET` steht für Address Family Internet. Es ist ein Adressfamilientyp, der für die Verwendung von IPv4-Adressen im Internet bestimmt ist.

**2. Was bedeutet SOCK_STREAM?**
- `SOCK_STREAM` gibt an, dass der Socket ein sequenzielles, zuverlässiges, bidirektionales, verbindungsorientiertes Byte-Stream-Service verwendet. Es ist der Typ für TCP-Sockets.

**3. Was müsste in socket(...) eingetragen werden, um einen UDP-Socket statt eines TCP-Sockets zu erzeugen?**
- Um einen UDP-Socket anstelle eines TCP-Sockets zu erstellen, sollte der dritte Parameter von `socket(...)` auf SOCK_DGRAM gesetzt werden. 
```c
int connectionfd = socket(AF_INET, SOCK_DGRAM, 0);
```

**4. Das Struct serverSockAddr ist vom Typ sockaddr_in. Wozu dient es?**
- Das Struct `serverSockAddr` vom Typ `sockaddr_in` dient dazu, die Adresse und Portinformationen des Servers zu speichern, mit dem der Socket verbunden wird.

**5. Was bewirken `htonl(...)` und `htons(...)`?**
- `htonl(...)` steht für "Host to Network Long" und konvertiert eine 32-Bit-Ganzzahl (Long) vom Host-Format in das Netzwerkformat (Byte-Reihenfolge). `htons(...)` steht für "Host to Network Short" und konvertiert eine 16-Bit-Ganzzahl (Short) vom Host-Format in das Netzwerkformat. Diese Funktionen sind erforderlich, um sicherzustellen, dass die Daten in der richtigen Byte-Reihenfolge übertragen werden, da verschiedene Architekturen unterschiedliche Reihenfolgen verwenden können.

**6. Was meint INADDR_LOOPBACK? Was könnte bei anderen Anwendungsfällen hier stehen?**
- `INADDR_LOOPBACK` ist eine spezielle Konstante, die die IP-Adresse des Loopback-Interfaces (localhost) repräsentiert. Es ist die IP-Adresse 127.0.0.1 und wird verwendet, um eine Verbindung zum lokalen Host herzustellen. In anderen Anwendungsfällen könnte hier eine andere IP-Adresse stehen, um sich mit einem anderen Host zu verbinden.

**7. Der Befehl bind(...) koppelt den Socket mit dem Addressinterface des Structs `serverSockAddr`. Dazu wird der File-Deskriptor des Sockets übergeben sowie die Adresse und Größe des Address-Structs. Welche Bedeutung hat der Ausdruck `(struct socketaddr*)`?**
- Der Ausdruck `(struct sockaddr*)` wird verwendet, um einen Zeiger auf das `serverSockAddr`-Struct in einen Zeiger auf sockaddr (die Basisklasse für verschiedene Arten von Socket-Adressen) zu konvertieren. Dies ermöglicht die Übergabe des Structs an die `bind(...)`-Funktion, die ein sockaddr-Zeiger erwartet.


**8. Um eine Verbindungsanfrage eines Clients zu akzeptieren, wird `accept(...)` aufgerufen. Weshalb wird diesem Befehl die Adresse eines uninitialisierten Structs `sockaddr_in` übergeben?**
- Dem Befehl `accept(...)` wird die Adresse eines uninitialisierten struct `sockaddr_in` übergeben, um die Informationen des Clients zu erhalten, der eine Verbindungsanfrage sendet. Der `accept(...)`-Befehl füllt dieses Struct mit den Details des Clients (IP-Adresse und Port), um die Verbindung herzustellen.

## Aufgabe 3

Vervollständigen Sie den Code des TCP-Clients in der main(). Gehen Sie wie folgt vor:
1. Erzeugen Sie einen Socket für TCP-Verbindungen.
2. Erzeugen Sie ein Struct vom Typ sockaddr_in, in das Sie die Adresse und den Port des Servers eintragen.
3. Nutzen Sie den Befehl connect(...), um den Client-Socket mit dem Interface des Server-Sockets zu verbinden.
4. Schließen Sie die Socketverbindung nach Benutzung mit close(...). 

Hinweis: Orientieren Sie sich an dem Code des Servers. Nutzen Sie Konsolenausgaben, um beim Ausführen den Erfolg der einzelnen Schritte zu überprüfen. Nutzen Sie hierzu die Rückgabewerte der Funktionen socket(...) und connect(...).

## Aufgabe 4

Wir möchten nun simulieren, dass der – in unserer Vorstellung sehr rechenstarke – Server Rechenanfragen eines – in unserer Imagination leistungsschwachen – Clients beantwortet. Der Server ist in diesem Fall ein Primzahlengenerator. Modifizieren Sie Ihren Code in beiden Projekten derart, dass der Client angibt, die wievielte Primzahl er benötigt. Der Server sendet ihm diese dann zu. Der Client soll diese Zahl nach Empfang auf der Konsole ausgeben. Das Programm soll solange in einer Schleife laufen, bis der Client über Eingabe des Wortes „exit“ die Übertragung beendet.

Hinweis: Die Funktion write(...) erwartet als zweiten Parameter einen Verweis auf einen Puffer. Sie können daher nicht einfach einen Integer versenden. Die Funktion strtol(...) wandelt ein char-Array in einen Integer, die Funktion snprintf(...) einen Integer in ein char-Array.

## Aufgabe 5

Modifizieren Sie Ihren Code nun so, dass Sie nicht für jede Anfrage des Clients alle Primzahlen bis zur Benötigten neu generieren, sondern führen Sie alle in vorigen Anfragen erzeugten Primzahlen in einem dynamisch wachsenden Array mit. Fragen Sie vor Generierung erst ab, ob die benötigte Primzahl nicht ggf. schon in der Liste vorhanden ist.

Hinweis: Strukturieren Sie Ihr Programm in einzelne Funktionen. Eine Funktion verwaltet das Array/die Liste der bereits erzeugten Primzahlen. Eine andere Funktion übernimmt die eigentliche Generierung und ruft nach Auffinden einer neuen Primzahl die Funktion zur Verwaltung auf, um die neue Primzahl der Liste hinzuzufügen. Eine weitere Funktion soll am Endes Programms den dynamisch allokierten Speicher der Primzahlliste wieder freigeben.

Hinweis 2: Die Liste der generierten Primzahlen kann man wahlweise als Array oder als verkettete Liste verwalten. In beiden Fällen bietet sich an, eine eigene Struktur (struct) anzulegen. Definieren Sie dieses Struct mittels typedef als eigenen Variablentyp, um sich Schreibarbeit zu sparen.

