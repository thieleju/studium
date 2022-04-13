package Programmiertechnik_II.Übung2;

public class TurboLernenFuerPruefungen {

  public void lernen(boolean nochGenugZeit) throws PruefungsPanik {

    if (!nochGenugZeit)
      throw new PruefungsPanik("In der Ruhe liegt die Kraft.");

    System.out.println("Großartiges Zeitmanagement. Weiter so!");
  }
}
