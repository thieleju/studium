package Programmiertechnik_I.PolymorphieTest;

public class Auto extends Fahrzeug {

  public Auto(String marke) {
    super("Auto", marke);
  }

  public void fahren() {
    System.out.println(fahrzeugTyp + " der marke " + marke + " fährt");
  }

}
