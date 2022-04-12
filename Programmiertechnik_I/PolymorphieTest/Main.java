package Programmiertechnik_I.PolymorphieTest;

public class Main {

  public static void main(String[] args) {

    Fahrzeug[] fahrzeuge = new Fahrzeug[3];

    fahrzeuge[0] = new Auto("BMW");
    fahrzeuge[1] = new Roller();
    fahrzeuge[2] = new Auto("Audi");

    for (Fahrzeug f : fahrzeuge) {
      f.fahren();
    }

  }
}
