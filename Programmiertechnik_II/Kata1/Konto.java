public class Konto {

  private int kontoNummer;

  public Konto() {
  }

  public Konto(int kontoNummer) {
    this.kontoNummer = kontoNummer;
  }

  // copy constructor
  public Konto(Konto konto) {
    this(konto.kontoNummer);
  }

  public int getNummer() {
    return kontoNummer;
  }

  public void setNummer(int kontoNummer) {
    this.kontoNummer = kontoNummer;
  }

}
