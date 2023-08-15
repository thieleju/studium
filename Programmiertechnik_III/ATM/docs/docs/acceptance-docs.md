# Abnahmedokumentation

## System Under Test

</br>
System Under Test bezieht sich auf die Validierung des Systems.
</br>
</br>
Das System wird unter verschiedenen Szenarien getestet. Die Anforderungsspezifikationen werden den Testfällen zugeordnet, um zu überprüfen, ob alle Anforderungen erfüllt sind.
</br>
</br>
</br>
Die folgende Tabelle beinhaltet die Testfälle und Testergebnisse. Für detallierte Testspezifikationen siehe [Testdokumentation](test-docs.md).
</br>
</br>
Bestanden: Testergebnisse wie erwartet
</br>
Nicht bestanden: Testergebnisse nicht wie erwartet
</br>
</br>

| Testfall                       | Testergebnis |
| ------------------------------ | ------------ |
| Sind Komponenten initialisiert | Bestanden    |
| Wechsel in BALANCE Modus       | Bestanden    |
| Falscher Input in Menü         | Bestanden    |
| "Back" Button                  | Bestanden    |
| "Clear" Button                 | Bestanden    |
| Ungültiger Pin Input           | Bestanden    |
| Neuen Account erstellen        | Bestanden    |
| Credit und Debit Funktion      | Bestanden    |

</br>
Die folgende Tabelle beinhaltet die User Stories und deren Ergebnisse. Für detallierte User Stories siehe [Anforderungsdokumentation](requirements-docs.md).
</br>
</br>
Implementiert: User Stories erfolgreich implementiert
</br>
Nicht implementiert: User Stories nicht erfolgreich implementiert
</br>
</br>

| Nr. | User Stories                                      | Testergebnis                  |
| --- | ------------------------------------------------- | ----------------------------- |
| 1   | ...Verschiedene Geldbeträge eingeben...           | Implementiert                 |
| 2   | ...Sehen, wie viel Geld auf Konto ist...          | Implementiert                 |
| 3   | ...maximal Debit Betrag pro Tag festlegen...      | Implementiert                 |
| 4   | ...vierstelligen Pin zu meiner Karte eingeben...  | Implementiert                 |
| 5   | ...Ziffern meiner Pin ändern...                   | Implementiert (Administrator) |
| 6   | ...Länge meiner Pin ändern...                     | Nicht implementiert           |
| 7   | ...Stückelung auswählen...                        | Nicht implementiert           |
| 8   | ...in mein Konto einloggen...                     | Implementiert                 |
| 9   | ...gegen Gebühren Geld abheben...                 | Nicht implementiert           |
| 10  | ...vollständige und detaillierte Dokumentation... | Implementiert                 |

</br>

## Bereitstellung zur Abnahme

Das Abnahmeprotokoll kann [hier](https://github.com/thieleju/ATM/raw/main/abnahmeprotokoll.pdf) als PDF-Datei heruntergeladen werden.

![Exceptions](images/abnahmeprotokoll.png "Abnahmeprotokoll")
