package Programmiertechnik_II.Übung1;

public class Main {

  public static void main(String[] args) {

    // Aufgabe 2
    Auto auto = new Auto("ABC-123", 0, 50, 10, new Reifen("Pirelli", "Pirelli"));
    auto.fahren(600);
    auto.tanken(70);

  }
}
