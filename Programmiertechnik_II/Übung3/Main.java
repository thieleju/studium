
public class Main {

  public static void main(String[] args) {

    // A1
    // Haustier ist nicht instanziierbar, da abstrakt
    // Haustier h = new Haustier();
    Hund hund = new Hund();
    Schaf schaf = new Schaf();
    Hahn hahn = new Hahn();

    // A2
    // Abstrakte Methoden können nur in Abstrakten Klassen vorkommen

    // I1
    // Der "Deadly Diamond of Death“ ist ein Problem, das bei Mehrfachvererbung
    // auftritt. Beispiel: A erbt von B und C, B und C erben von D

    // I2
    // Mehrfachvererbung ist in Java nicht erlaubt

    // I6
    // Im Prinzip gehts nur darum, dass die Funktionen eines Interfaces
    // überschrieben werden.
    Konto konto = new Konto(1234);

    Kunde k1 = new Kunde("Kunde 1", konto);
    System.out.println(k1);
    Kunde k2 = k1;
    System.out.println(k2);
    Kunde k3 = k1.clone();
    System.out.println(k3);
  }

}
