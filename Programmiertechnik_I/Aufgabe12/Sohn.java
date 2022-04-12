package Programmiertechnik_I.Aufgabe12;

public class Sohn extends Vater {

  private int sohnVar;
  private int var;

  public Sohn() {
    sohnVar = 2;
    var = 2;
  }

  public void zeigeVar() {
    System.out.println("SOHN: sohnVar: " + sohnVar);
    System.out.println("SOHN: welches var?: " + var);
    System.out.println("SOHN: super " + super.var);
  }

}
