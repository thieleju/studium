package Programmiertechnik_I.Aufgabe12;

public class Aufgabe12 {

  public void print() {
    // 12.1 (Sichtbarkeit von Variablen) - Gegeben seien folgende Klassen.
    // Was ist die Ausgabe in Zeile 05 und 06?
    // 5)
    // VATER: varterVar: 1
    // VATER: var: 1
    // 6)
    // SOHN: sohnVar: 2
    // SOHN: welches var?: 2
    // SOHN: super 1 (da super.var)

    Vater vater = new Vater();
    Sohn sohn = new Sohn();
    vater.zeigeVar();
    sohn.zeigeVar();

    // 12.2
    // (Lebensdauer und Sichtbarkeit von Variablen) Gegeben seien folgende Klassen
    // Wie viele Buch- bzw. Verlagsobjekte leben zum Ausführungszeitpunkt der
    // jeweiligen Zeilen?

    Verlag verlag1 = new Verlag("Verlag 1");
    Verlag verlag2 = new Verlag("Verlag 2");

    Buch buch1 = new Buch(verlag1);
    Buch buch2 = new Buch(verlag1);
    Buch buch3 = new Buch(verlag2);
    Buch buch4 = buch2;

    buch4 = null;
    buch2 = buch4;
    buch4 = buch1;

    VerlagBuchTest.machZeugMitBuch(buch3); // Hat Buch 3 noch einen Verlag?
    System.out.println(buch1.getVerlag().getName());
    System.out.println(buch3.getVerlag().getName());
    System.out.println(buch4.getVerlag().getName());
    // System.out.println(buch2.getVerlag().getName());

  }
}
