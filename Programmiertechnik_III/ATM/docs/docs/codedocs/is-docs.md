# Code Ist-Dokumentation

Die Beschreibung in diesem Dokument ist zusätzlich zu dem kommentierten Code im Ordner `ATM-Machine-Old`.

## Klassen

`ATMCaseStudy.java`

- Erstellt eine ATM Instanz und startet diese, wenn noch keine vorhanden

`ATM.java`

- Stellt die Hauptklasse des ATMs dar
- Initialisiert UI mit Keypad, CashDispenser, DepositSlot und Bankdatabase
- Es gibt viele unbenutzte konstante int Variablen
- Sobald Enter betätigt wird, wird die PIN überprüft (login)
- Wenn man eingeloggt ist, wird das Menü angezeigt, wenn man als Admin eingeloggt ist, wird das Admin-Menü angezeigt
- Im Menü kann man nun zwischen Funktionen wählen:
      - `balance`: Eigenes Guthaben anzeigen
      - `withdrawal`: Geld abheben, indem man die Scheine einzeln wählt
      - `deposit`: Geld einzahlen. Geld ist erst verfügbar, wenn überprüft.
      - `exit`: Führt Login erneut aus, öffnet allerdings neues Fenster
- Sollte man als Admin angemeldet sein, öffnet sich die Adminoberfläche mit diesen Funktionen:
      - Kontostand jedes Nutzers einsehen
      - Zwischen Accounts wecheln
      - Accounts löschen
      - Neue Accounts hinzufügen

`Transaction.java`

- Abstrakte Klasse, die mit einer AccountNummer, dem Screen-Objekt und dem BankDatabase-Objekt initialisiert wird.

`BalanceInquiry.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion gibt den Kontostand auf dem Screen aus

`Withdrawal.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion zeigt die Buttons zur Scheinauswahl an
- Die Transaction-Funktion ermöglicht das Abheben von Geld, wenn noch genügend auf dem Konto und im CashDispenser verfügbar ist
- Man kann nur in 20er Scheinen abheben

`Deposit.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion zeigt UI zum Geldeinzahlen an
- Beim Geldeinzahlen wird geprüft, ob das Geld eingezahlt wurde

`DepositSlot.java`

- Klasse ist nicht vorhanden
- Hier sollte überprüft werden, ob das Geld vorhanden ist

`CashDispenser.java`

- Startet mit 500 20$ Scheinen

`BankDatabase.java`

- Initialisiert alle Accounts
- Authentifiziert Nutzer anhand der PIN
- Funktionen um anhand der AccountNummer Daten über den Account abzurufen (verfügbares Guthaben, etc)
- Besitzt Funktionen um Guthaben von Accounts abzuziehen oder aufzuladen
- Fehler: `getaccpin` funktioniert nicht
- Funktion um temporär einen Account zu erstellen und dem Account-Array hinzuzufügen
- Funktion um temporär einen Account zu löschen

`Account.java`

- Besitzt Eigenschaften eines Benutzers
- Funktion um Pin mit aktuellem Account zu verifizieren
- Getter und Setter

`AccountFactory.java`

- Wird nicht verwendet
- Erbt von Account, initialisiert einen Account

`Iterator`

- Interface, das zwei Funktionen beinhaltet, die einen Wahrheitswert zurückgeben, ob von der aktuellen Position ein nächstes oder vorheriges Element existiert
- Funktion, die ein Objekt zurück gibt, anhand einer Position

`AccountIterator.java`

- Implementiert das Iterator Interface und überschreibt dessen Funktionen

`Screen.java`

- JFrame-Komponente, die Textfelder, Labels und Buttons besitzt
- Besitzt Funktionen um Nachrichten in der Konsole auszugeben
- Besitzt Funktionen um UI-Elemente anzuzeigen:
      - Login
      - Menü
      - Kontostand
      - Geldauszahlung
      - Geldeinzahlung
      - Admin-Ansicht

`Keypad.java`

- Besitzt unbenutze Scanner-Funktion
- Besitzt JButtons für ein Tastenfeld mit Löschen und Enter Funktionen
- Funktion, um ein JPanel mit Buttons zu initialisieren und zurückgeben
- Fehler: Endlos-Schleife `userinput()`
