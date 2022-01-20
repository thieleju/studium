package Aufgabe10;

public class Studierende {

  private String name;
  private int matrikelnummer;
  private int geburtsjahr;
  private static int anzahlStudierende = 0;

  public Studierende(int geburtsjahr) {
    this.geburtsjahr = geburtsjahr;
    anzahlStudierende++;
  }

  public Studierende(String name, int martrikelnummer, int geburtsjahr) {
    this.name = name;
    this.matrikelnummer = martrikelnummer;
    this.geburtsjahr = geburtsjahr;
    anzahlStudierende++;
  }

}
