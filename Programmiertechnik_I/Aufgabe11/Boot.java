package Aufgabe11;


public class Boot {

  private double groesse;
  private String name;

  public Boot(double g, String n) {
    groesse = g;
    name = n;
  }

  public String toString() {
    return name + " | Groesse: " + groesse + "cm";
  }

  public double getGroesse() {
    return groesse;
  }

  public String getName() {
    return name;
  }

  public void setGroesse(double g) {
    this.groesse = g;
  }

  public void setName(String n) {
    this.name = n;
  }

}
