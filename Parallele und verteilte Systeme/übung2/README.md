# Aufgabe 1

Lesen Sie das folgende Programm und versuchen Sie, die folgenden Fragen zu beantworten ohne
das Programm auszuführen.

Was wird dieses Programm ausgeben?
- X hat den Wert 5 und Y den Wert 10

Darf ich in main() auch zwei Variablen namens a und b vom Typ int anlegen und
vertausche(a, b) aufrufen?
- Nein, da sie nicht global und dadurch in der Funktion nicht erreicht werden können

# Aufgabe 2

Nachdem Sie für sich die Frage aus Aufgabe 1 beantwortet haben, kopieren Sie den obigen Code in ein neues Eclipse-Projekt und führen Sie es aus. Was stellen Sie fest? Können Sie sich das Verhalten erklären? 
- Die Variablen werden nicht vertauscht, da die Funktion nur eine Kopie der Variablen erzeugt und diese Kopie vertauscht


Dieses Verhalten wird durch den Funktionsaufruf hervorgerufen, der als sog. **Call-by-Value** umgesetzt wird.

# Aufgabe 4

Welchen Wert hat z nach Aufruf der Funktion berechneZ()?
- z ist nicht definiert

Was, glauben Sie, wird passieren, wenn Sie versuchen, das Programm in Eclipse auszuführen?
- Es wird ein Fehler ausgegeben

# Aufgabe 7

Mit den Erkenntnissen aus den Aufgaben 1 bis 3: Was wird dieses Programm ausgeben?
- Da hier Call-by-Reference verwendet wird, werden die Variablen vertauscht

Darf man in main() das Array auch als int Elefant[2] anlegen oder muss es int myArray[2] heißen?
- Die Variable kann einen beliebigen gültigen Namen haben

Was wird passieren, wenn in der Funktion vertauscheImArray() folgende Zeile steht: myArray[0] = myArray[17];?
- Es wird ein Fehler ausgegeben, da der Array nur 2 Elemente hat

# Aufgabe 9

Lassen Sie sich das Verhalten, das das Programm aus Aufgabe 7 zeigt, erklären.
- Call-by-Reference

# Aufgabe 10

Warum sind globale Variablen statt Übergabeparameter keine gute Lösung?
- Sie können von anderen Funktionen geändert werden und sind dadurch 
  - schwerer zu debuggen
  - Sicherheitsrisiko

# Bonusaufgabe

```c
int main(void) {
  int p = 10, q[p];
  for(; q[--p]=p; );
  return 0;
}
```
Was passiert in diesem Code?
- Es wird ein Array mit 10 Elementen erzeugt mit den Werten 0 bis 9 

Wo ist bei der for-Schleife der Abschnitt mit den geschweiften Klammern?
- Wird mit ';' am Ende der Zeile übersprungen, da kein Codeblock vorhanden ist

Warum ist das erste Zeichen in der runden Klammer ein Semikolon?
- Es gibt keine initilisierte Variable für die for-Schleife

Wo ist der Unterschied zwischen --p und p--?
- p-- wird erst nach der Verwendung von p dekrementiert
- --p wird vor der Verwendung von p dekrementiert

Sind Sie in der Lage, den Code so umzuschreiben, dass Ihre Lieblingskommilitonin/Ihr Lieblingskommilitone den Code verstehen kann? 
(Schwierigkeit dieser Frage hängt ggf. von Ihrem Freundeskreis ab)

```c
int p = 10, q[p];
for (int i = 0; i < p; i++)
{
  q[i] = i;
}
```