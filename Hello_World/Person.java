package Hello_World;

public class Person {

  private int alter;
  private String name;
  private double einkommen;
  private char geschlecht;
  private boolean vegetarier;
  private static int menschenInDe = 83000000;

  public Person(int a, String n, double e, char g, boolean v) {
    this.alter = a;
    this.name = n;
    this.einkommen = e;
    this.geschlecht = g;
    this.vegetarier = v;
  }

  public void printInfo() {
    System.out.println("Name: " + this.name);
    System.out.println("Alter: " + this.alter);
    System.out.println("Einkommen: " + this.einkommen);
    System.out.println("Geschlecht: " + this.geschlecht);
    System.out.println("Vegetarier: " + this.vegetarier);
  }

}
