package Übungsaufgaben;

import java.util.ArrayList;

public class Aufgabe8 {

  // 8.1
  // Schreiben Sie eine Methode, die die Inhalte eines eindimensionalen
  // Integer-Arrays auf der Konsole ausgibt.
  public void print81(int[] array) {
    for (int i : array) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  // 8.2
  public int[] arrayCopy(int[] a) {
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      b[i] = a[i];
    }
    return b;
  }

  // 8.3 und 8.4
  // Schreiben Sie eine Methode, die ein integer-Array um eine Position
  // nach rechts bzw. nach links rotiert. (Alternativ können Sie auch zwei
  // Methoden mit Links- und
  // Rechtsrotation erstellen).
  public int[] rotate(int[] array, int n, boolean left) {
    int[] newArray = arrayCopy(array);
    if (left) {
      for (int i = 0; i < array.length; i++) {
        int pos = (i + n) % array.length;
        newArray[i] = array[pos];
      }
    } else {
      for (int i = 0; i < array.length; i++) {
        int pos = (i + array.length - n) % array.length;
        newArray[i] = array[pos];
      }
    }
    return newArray;
  }

  // 8.5
  // Schreiben Sie eine Methode, die solange eine Zufallszahl zwischen 1
  // und 6 generiert bis die gewünschte Würfel-Zahl (n) „gewürfelt“ wurde.
  // Geben Sie auf der Konsole aus, wie viele Versuche hierzu benötigt wurden.
  public int getRandInt(int fromNum, int toNum) {
    int rand = (int) (Math.random() * (toNum - fromNum + 1) + fromNum);
    return rand;
  }

  public void zahlWuerfeln(int n) {
    int count = 0;
    int zahl = getRandInt(1, 6);
    do {
      zahl = getRandInt(1, 6);
      count++;
    } while (zahl != n);

    System.out.println("Versuche: " + count);
  }

  // 8.6 und 8.7
  // Schreiben Sie eine Methode, die solange zwei Zufallszahlen zwischen 1 und 6
  // generiert (simuliert das Würfeln) bis hintereinander dieselbe Zahl
  // gewürfelt wurde. Geben Sie auf der Konsole aus, wie viele Versuche hierzu
  // benötigt wurden.
  public void doppelteZahlenWürfeln(int n) {
    int count = 0;
    int zahl1 = getRandInt(1, 6);
    int zahl2 = getRandInt(1, 6);

    do {
      zahl1 = getRandInt(1, 6);
      zahl2 = getRandInt(1, 6);
      count++;
    } while (zahl1 != n || zahl2 != n);

    System.out.println("Versuche: " + count);
  }

  // 8.8
  // Gegeben seien drei Wortgruppen mit „Teilsätzen“. Schreiben Sie ein
  // Programm, welches aus den drei Wortgruppen jeweils einen Teilsatz auswählt
  // und zu einem ganzen Satz kombiniert. Gerne können Sie die Wortgruppen um
  // eigene Teilsätze ergänzen ;-)
  public void wortgruppen() {
    String wortgruppe1 = "Mut ist, Leichtsinn ist, Verantwortung ist, Demokratie ist, Geld ist, Liebe ist, Betrug ist, Freiheit ist, Denken ist, Selbstlosigkeit ist, Rekursion ist, Egoismus ist, Moral ist, Glück ist, Konsum ist, Das Leben ist, Das Schicksal ist, Unabhängigkeit ist, Gerechtigkeit ist, Der Tod ist, Programmiertechnik ist";
    String wortgruppe2 = "letztlich, sozusagen, am Ende des Tages, quasi, genaugenommen, wirklich, leider, zum Glück, durch die Blume gesagt, rückblickend, voraussichtlich, unter Umständen, erwiesenermaßen, gelinde gesagt, strenggenommen, unterm Strich, in gewisser Hinsicht, primär, mitnichten, vor allem ";
    String wortgruppe3 = "die höchste Tugend, die Basis des Zusammenlebens, eine gesellschaftliche Pflicht, der Anfang allen Übels, (das, wofür es sich zu leben lohnt), falsch, zutiefst menschlich, kein Kavaliersdelikt mehr, völlig überschätzt, völlig unterschätzt, immer Gabe und Aufgabe zugleich, mit Vorsicht zu genießen, fragwürdig, existenzgefährdend, der Schlüssel zum Glück, eine Frage des Anstands, jedem selbst überlassen, eine Fügung, völlig willkürlich, immer schon vorherbestimmt, nicht mehr haltbar, unsere Rettung, eine Illusion, völlig überholt, nur die Spitze des Eisbergs";

    String[][] wortgruppen = { wortgruppe1.split(", "), wortgruppe2.split(", "), wortgruppe3.split(", ") };

    String satz = "";
    for (String[] wg : wortgruppen) {
      satz += wg[getRandInt(0, wg.length - 1)] + " ";
    }
    System.out.println(satz);
  }

  // 8.9
  // Versuchen Sie, ohne den Code auszuführen herauszufinden, was die Methode
  // macht. Lassen Sie den Code laufen. Hat sich Ihre Annahme bestätigt?
  // Machen Sie eine Skizze, die hilft zu verstehen, was passiert. Alternativ zur
  // Skizze: Beschreiben Sie natürlichsprachlich, wie der Algorithmus
  // funktioniert. Kommentieren Sie den
  // Code derart, dass dieser einfach verständlich ist.
  public int[] bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      // loop through inner array
      for (int j = 0; j < arr.length - i - 1; j++)
        // check if swap is required
        if (arr[j] > arr[j + 1]) {
          // do swap arr[j] with arr[j + 1]
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
    }
    return arr;
  }
}
