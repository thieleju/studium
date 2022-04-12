package Programmiertechnik_II.Übung1;

public class Main {

  public static void main(String[] args) {

    // Aufgabe 2
    Auto auto = new Auto("ABC-123", 0, 50, 10, new Reifen("Pirelli", "Pirelli"));
    auto.fahren(600);
    auto.tanken(70);

    // Aufgabe 3
    // "Test" mit welchem hinter b2 nur eine Referenz auf b1 ist
    Buch b1 = new Buch("Der Heimweg", 3426281554L, 22.99, null);
    Buch b2 = b1;
    // b2 ist weiterhin eine Referenz auf b1
    b1.setTitel("Die Rückkehr");
    b2.setTitel("Der steinige Weg des Kopierens");
    System.out.println(b1.getTitel());
    System.out.println(b2.getTitel());
    // Es werden 2 Objekte erzeugt
    // Es wird bewirkt, dass ein neues Objekt erzeugt wird und mit den Werten von b1
    // befüllt wird
    Buch b3 = new Buch(b1);

    // Zweite Testklasse:
    // - b1 wird kopiert in ein neues Objekt b2

    // Aufgabe 4

  }
}
