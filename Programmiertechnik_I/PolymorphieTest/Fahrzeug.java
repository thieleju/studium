package PolymorphieTest;

public class Fahrzeug {

  protected String fahrzeugTyp;
  protected String marke;

  public Fahrzeug(String fahrzeugTyp) {
    this.fahrzeugTyp = fahrzeugTyp;
  }

  public Fahrzeug(String fahrzeugTyp, String marke) {
    this.fahrzeugTyp = fahrzeugTyp;
    this.marke = marke;
  }

  public void fahren() {
    System.out.println(fahrzeugTyp + " fährt");
  }

}
