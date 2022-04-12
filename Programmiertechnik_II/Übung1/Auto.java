package Programmiertechnik_II.Übung1;

public class Auto {

  private String kennzeichen;
  private double kilometerstand;
  private double tankvolumen;
  private double tankinhalt;
  private double verbrauch;
  private Reifen reifen;

  public Auto(String kennzeichen, double kilometerstand, double tankvolumen, double verbrauch, Reifen reifen) {
    this.kennzeichen = kennzeichen;
    this.kilometerstand = kilometerstand;
    this.tankvolumen = tankvolumen;
    this.tankinhalt = tankvolumen;
    this.verbrauch = verbrauch;
    this.reifen = reifen;
  }

  public void fahren(double strecke) {
    if (tankinhalt - strecke / verbrauch < 0) {
      // Tank reicht nicht aus
      strecke = tankinhalt * verbrauch;
      System.out.println("Es muss getankt werden");
    } else {
      // Tank reicht aus
      kilometerstand += strecke;
    }
    double literVerbraucht = strecke / verbrauch;
    tankinhalt -= literVerbraucht;
    System.out.println("Es wurden " + strecke + "km gefahren und " + literVerbraucht + "l verbraucht");
  }

  public void tanken(double liter) {
    if (tankinhalt + liter > tankvolumen) {
      System.out.println("Tank ist voll.");
      liter = tankvolumen - tankinhalt;
    }
    tankinhalt += liter;
    System.out.println("Es wurden " + liter + "l getankt. Aktueller Tankinhalt: " + tankinhalt + "l");
  }

}
