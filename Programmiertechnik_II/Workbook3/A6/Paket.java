package Programmiertechnik_II.Workbook3.A6;

public class Paket implements Comparable<Paket> {

  private String absender;
  private double gewicht;
  private double groesse;

  public Paket(String absender, double gewicht, double groesse) {
    this.absender = absender;
    this.gewicht = gewicht;
    this.groesse = groesse;
  }

  @Override
  public int compareTo(Paket p) {
    if (this.gewicht > p.gewicht)
      return -1;
    else if (this.gewicht < p.gewicht)
      return 1;
    else
      return 0;
  }

  public String toString() {
    return "Absender: " + absender + " Gewicht: " + gewicht + " Groesse: " + groesse + "\n";
  }

}
