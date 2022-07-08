public class Kunde {

  private String name;
  private Konto konto;

  public Kunde() {
  }

  public Kunde(String name) {
    this.name = name;
  }

  public Kunde(String name, Konto konto) {
    this(name);
    // using copy constructor
    this.konto = new Konto(konto);
  }

  public String getName() {
    return name;
  }
}
