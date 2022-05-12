package Programmiertechnik_II.Workbook1.I6;

public class Kunde implements Cloneable {

  private String name;
  private Konto konto;

  public Kunde(String name, Konto konto) {

    this.name = name;
    this.konto = konto;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Konto getKonto() {
    return konto;
  }

  public void setKonto(Konto konto) {
    this.konto = konto;
  }

  public String toString() {
    return name + ": " + konto;
  }

  @Override
  public Kunde clone() {
    Kunde k = null;
    try {
      k = (Kunde) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return k;
  }

}
