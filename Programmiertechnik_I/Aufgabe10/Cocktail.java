package Programmiertechnik_I.Aufgabe10;

public class Cocktail {

  private String name;
  private String[] zutaten;
  private boolean istGeheimrezept;

  public Cocktail(String name) {
    this.name = name;
  }

  public Cocktail(String name, String[] zutaten) {
    this.name = name;
    this.zutaten = zutaten;
  }

  public boolean istGeheimrezept() {
    return this.istGeheimrezept;
  }

  public void printNameUndZutaten() {
    if (istGeheimrezept()) {
      System.out.println("pssst...");
    } else {
      System.out.println(this.name);
      for (String zutat : this.zutaten) {
        System.out.println(zutat);
      }
    }

  }

  public String getName() {
    return this.name;
  }

  public String[] getZutaten() {
    return this.zutaten;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setZutaten(String[] zutaten) {
    this.zutaten = zutaten;
  }
}
