package Programmiertechnik_I.Aufgabe11;

public class Kunde {

  private String name;
  private int alter;
  private Kunde vorherigerKunde;
  private int kundenNummer;
  public static int folgeNummer = 0;

  // a
  private Kunde() {
    this.kundenNummer = folgeNummer;
    folgeNummer++;
  }

  // b
  public Kunde(String name) {
    this();
    this.name = name;
  }

  // c
  public Kunde(String name, int alter) {
    this(name);
    this.alter = alter;
  }

  // 7.
  public Kunde(String name, int alter, Kunde k) {
    this(name, alter);
    this.vorherigerKunde = k;
  }

  public boolean istErster() {
    return this.vorherigerKunde == null;
  }

  // 9.

  public int getKundenNummer() {
    return this.kundenNummer;
  }

  public Kunde getVorherigerKunde() {
    return this.vorherigerKunde;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAlter() {
    return this.alter;
  }

  public void setAlter(int alter) {
    this.alter = alter;
  }

  // 10.
  public void printKunde() {
    System.out.println("Kunde: " + this.name + " (" + this.alter + ")");
  }

  // 11.
  public String toString() {
    String ausgabe = name + " " + kundenNummer + "";
    if (vorherigerKunde != null) {
      ausgabe = ausgabe + " kommt nach " + vorherigerKunde;
    }
    return ausgabe;
  }

}
