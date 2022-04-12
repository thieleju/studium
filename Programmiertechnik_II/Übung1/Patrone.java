package Programmiertechnik_II.Übung1;

class Patrone {
  private Boolean istSchwarzweiss;

  public Patrone(Boolean istSchwarzweiss) {
    this.istSchwarzweiss = istSchwarzweiss;
  }

  // Copy-Konstruktor
  public Patrone(Patrone p) {
    this.istSchwarzweiss = p.getIstSchwarzweiss();
  }

  public Boolean getIstSchwarzweiss() {
    return istSchwarzweiss;
  }

  public void setIstSchwarzweiss(Boolean istSchwarzweiss) {
    this.istSchwarzweiss = istSchwarzweiss;
  }

}