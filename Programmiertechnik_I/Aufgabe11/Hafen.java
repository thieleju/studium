package Programmiertechnik_I.Aufgabe11;

// 6. Definieren Sie eine Klasse Hafen, die eine private Instanzvariable hat,
// die ein Array von Booten darstellt (das sind die Liegeplätze am Hafen).
// Definieren Sie einen Konstruktor, der mit der Anzahl der Liegeplätze
// aufgerufen wird und das Array initialisiert. Einen parameterlosen Konstruktor
// soll es nicht geben.
// Definieren Sie eine private Klassen-Variable nochFreiePlaetze, die die Anzahl
// freier Plätze speichert. Erweitern Sie den Konstruktor derart, dass beim
// Erstellen des Hafens, die
// Klassenvariable nochFreiePlaetze initialisiert wird.
// Schreiben Sie eine Methode getAnzahlLiegeplaetze, die die Anzahl noch freier
// Liegeplätze angibt.
// Schreiben Sie eine Methode nochFreiePlaetze, die true zurückliefert, falls
// mindestens ein Liegeplatz im Hafen frei ist.
// Schreiben Sie eine Methode
// public int addBoot (Boot b), 
// die dem Boot einen Liegeplatz zuweist falls noch
// einLiegeplatz frei ist. Die Methode gibt die Liegeplatznummer zurück wenn ein
// Platz frei ist, -1 sonst.
// Schreiben Sie eine Methode, die das größte Boot und eine Methode die das
// kleinste Boot zurückgibt.

public class Hafen {

  private Boot[] liegeplaetze;

  private static int nochFreiePlaetze;

  public Hafen(int anzahlLiegeplaetze) {
    liegeplaetze = new Boot[anzahlLiegeplaetze];
    nochFreiePlaetze = anzahlLiegeplaetze;
  }

  public int getAnzahlLiegeplaetze() {
    return nochFreiePlaetze;
  }

  public boolean nochFreiePlaetze() {
    return nochFreiePlaetze > 0;
  }

  public int addBoot(Boot b) {
    if (nochFreiePlaetze > 0) {
      nochFreiePlaetze--;
      liegeplaetze[nochFreiePlaetze] = b;
      return nochFreiePlaetze;
    }
    return -1;
  }

  public Boot getGroessteBoot() {
    double groessteGroesse = 0;
    int groessteIndex = 0;

    for (int i = 0; i < liegeplaetze.length; i++) {
      if (liegeplaetze[i] != null) {
        // liegeplatz belegt
        if (liegeplaetze[i].getGroesse() > groessteGroesse) {
          // setze groessteGroesse und Index wenn groesser
          groessteGroesse = liegeplaetze[i].getGroesse();
          groessteIndex = i;
        }
      }
    }
    // gebe das groesste Boot zurueck
    return liegeplaetze[groessteIndex];
  }

  public Boot getKleinsteBoot() {
    double kleinsteGroesse = Double.POSITIVE_INFINITY;
    int kleinsteIndex = 0;

    for (int i = 0; i < liegeplaetze.length; i++) {
      // liegeplatz belegt
      if (liegeplaetze[i] != null) {
        if (liegeplaetze[i].getGroesse() < kleinsteGroesse) {
          // setze kleinsteGroesse und Index wenn kleiner
          kleinsteGroesse = liegeplaetze[i].getGroesse();
          kleinsteIndex = i;
        }
      }
    }
    // gebe das kleinste Boot zurueck
    return liegeplaetze[kleinsteIndex];
  }

  private void printLiegePlaetze(Boot[] lp) {
    for (int i = lp.length - 1; i >= 0; i--) {
      if (lp[i] != null) {
        System.out.println(lp[i]);
      }
    }
  }

  public void printHafen() {
    System.out.println("--> Hafen:");
    printLiegePlaetze(this.liegeplaetze);
  }

  public void printBubbleSortedHafenByGroesse() {
    // copy liegeplaetze Boot array
    Boot[] newLiegeplaetze = new Boot[liegeplaetze.length];
    for (int i = 0; i < liegeplaetze.length; i++) {
      newLiegeplaetze[i] = liegeplaetze[i];
    }

    // outer loop
    for (int i = 0; i < newLiegeplaetze.length; i++) {
      // inner loop
      for (int j = 0; j < newLiegeplaetze.length - 1; j++) {
        // compare groesse of element j and the one after j
        if (newLiegeplaetze[j].getGroesse() > newLiegeplaetze[j + 1].getGroesse()) {
          // swap boote
          Boot temp = newLiegeplaetze[j];
          newLiegeplaetze[j] = newLiegeplaetze[j + 1];
          newLiegeplaetze[j + 1] = temp;
        }
      }
    }
    // Print sorted hafen
    System.out.println("--> Hafen sorted by Groesse:");
    printLiegePlaetze(newLiegeplaetze);

  }

}
