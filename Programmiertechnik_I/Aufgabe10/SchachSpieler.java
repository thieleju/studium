package Aufgabe10;

public class SchachSpieler {

    private String name;
    private int alter;
    private int startNummer;
    private static int schachspielerAnzahl = 0;

    public SchachSpieler(String name, int alter) {
        this.name = name;
        this.alter = alter;
        schachspielerAnzahl++;
        this.startNummer = schachspielerAnzahl;
    }

    public int getStartnummer() {
        return startNummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

}
