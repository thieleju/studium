package Aufgabe12;

public class Vater {

  private int vaterVar;
  protected int var;

  public Vater() {
    vaterVar = 1;
    var = 1;
  }

  public void zeigeVar() {
    System.out.println("VATER: vaterVar: " + vaterVar);
    System.out.println("VATER: var: " + var);
  }

}
