package Übungsaufgaben.Aufgabe10;

public class Mitarbeiter {
    private String name;
    private String vorname;
    private int alter;

    private static int anzahlMitarbeiter = 0;

    public Mitarbeiter(String name, String vorname, int alter) {
        this.name = name;
        this.vorname = vorname;
        this.alter = alter;
        anzahlMitarbeiter++;
    }

    public int getAnzahlMitarbeiter() {
        return anzahlMitarbeiter;
    }

    public boolean istAelter(Mitarbeiter akMitarbeiter) {
        if (this.alter > akMitarbeiter.getAlter())
            return true;
        else
            return false;
    }

    public String toString() {
        return this.vorname + " " + this.name + " ist " + this.alter + " Jahre alt.";
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setVorname(String v) {
        this.vorname = v;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setAlter(int a) {
        this.alter = a;
    }

    public int getAlter() {
        return this.alter;
    }
}
