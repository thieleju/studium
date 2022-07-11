package A6;

public class Rezept implements Comparable<Rezept> {

  private String name;

  public Rezept(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(Rezept r) {
    return this.name.compareTo(r.name);
  }

  public String toString() {
    return name;
  }

}
