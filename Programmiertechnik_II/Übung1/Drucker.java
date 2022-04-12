package Programmiertechnik_II.Übung1;

class Drucker {

  private String typ;
  private String marke;
  private Patrone schwarzweissPatrone;
  private Patrone farbigePatrone;

  public Drucker(String typ, String marke) {
    this.typ = typ;
    this.marke = marke;
  }

  public Drucker(String typ, String marke, Patrone schwarzweissPatrone, Patrone farbigePatrone) {
    this.typ = typ;
    this.marke = marke;
    this.schwarzweissPatrone = schwarzweissPatrone;
    this.farbigePatrone = farbigePatrone;
  }

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  public String getMarke() {
    return marke;
  }

  public void setMarke(String marke) {
    this.marke = marke;
  }

  public Patrone getSchwarzweissPatrone() {
    return schwarzweissPatrone;
  }

  public void setSchwarzweissPatrone(Patrone schwarzweissPatrone) {
    this.schwarzweissPatrone = schwarzweissPatrone;
  }

  public Patrone getFarbigePatrone() {
    return farbigePatrone;
  }

  public void setFarbigePatrone(Patrone farbigePatrone) {
    this.farbigePatrone = farbigePatrone;
  }

}