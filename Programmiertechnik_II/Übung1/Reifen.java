package Programmiertechnik_II.Übung1;

public class Reifen {

  private String typ;
  private String marke;

  public Reifen(String typ, String marke) {
    this.typ = typ;
    this.marke = marke;
  }

  // Copy-Konstruktor
  public Reifen(Reifen r) {
    this.typ = r.typ;
    this.marke = r.marke;
  }

}
