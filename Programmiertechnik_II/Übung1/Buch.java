package Programmiertechnik_II.Übung1;

public class Buch {

  private String titel;
  private long isbn;
  private double preis;
  private Verlag verlag;

  public Buch(String titel, long isbn, double preis, Verlag verlag) {
    this.titel = titel;
    this.isbn = isbn;
    this.preis = preis;
    this.verlag = verlag;
  }

  // "Copy-Konstruktor"
  public Buch(Buch b) {
    this.titel = b.titel;
    this.isbn = b.isbn;
    this.preis = b.preis;
    // this.verlag = new Verlag(b.getVerlag().getName());
    this.verlag = new Verlag(b.getVerlag());
  }

  public void setVerlag(Verlag verlag) {
    this.verlag = verlag;
  }

  public Verlag getVerlag() {
    return verlag;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public void setIsbn(long isbn) {
    this.isbn = isbn;
  }

  public void setPreis(double preis) {
    this.preis = preis;
  }

  public String getTitel() {
    return titel;
  }

  public long getIsbn() {
    return isbn;
  }

  public double getPreis() {
    return preis;
  }

}
