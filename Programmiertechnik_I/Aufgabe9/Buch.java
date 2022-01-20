package Aufgabe9;

public class Buch {

    private String titel = "dummy";
    private double preis = 0;
    private String isbn = "dummy";

    public String toSting() {
        return "Titel: " + this.titel + "\nPreis: " + this.preis + "\nISBN: " + this.isbn;
    }

    public void setTitle(String t) {
        this.titel = t;
    }

    public String getTitle() {
        return this.titel;
    }

    public void setPreis(double d) {
        this.preis = d;
    }

    public double getPreis() {
        return this.preis;
    }

    public void setISBN(String s) {
        this.isbn = s;
    }

    public String getISBN() {
        return this.isbn;
    }

}
