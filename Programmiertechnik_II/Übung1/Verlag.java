package Programmiertechnik_II.Übung1;

public class Verlag {

  private String name;

  public Verlag(String name) {
    this.name = name;
  }

  // Copy-Konstruktor
  public Verlag(Verlag v) {
    this.name = v.name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
