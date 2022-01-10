package Übungsaufgaben.Aufgabe10;

public class Aufgabe10 {

  public void print() {

    // 10.1
    Studierende s1 = new Studierende(1998);
    Studierende s2 = new Studierende("Julian", 12345, 1998);

    // 10.2
    // a) Mueller = new SchachSpieler(); wirft einen Fehler, da keine Paramter
    // b) Klassenvariablen(static) gehören einer ganzen Klasse (alle Objekte teilen)
    // c) private static int startNummer = 0; und startNummer++ im Konstruktor

    // 10.3
    int amount = 10;
    Mitarbeiter[] m = new Mitarbeiter[amount];

    for (int i = 0; i < amount; i++) {
      // neuer Mitarbeiter wird erzeugt und dem Array zugewiesen
      m[i] = new Mitarbeiter(Integer.toString(i), "Mitarbeiter", 23);
      // Aktueller Mitarbeiter wurde erzeugt
      System.out.println(m[i].getVorname() + " " + m[i].getName() + " wurde gerade erzeugt");
    }
    // gebe Anzahl Mitarbeiter aus
    System.out.println(m[0].getAnzahlMitarbeiter());

    // 10.4
    // 6. man müsste immer die gesamte neue Zutatenliste zuweisen, da sie immer neu
    // gesetzt werden muss

    // 10.5
    // na gut ...
  }
}
