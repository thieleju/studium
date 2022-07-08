public class TurboLernenFuerPruefungen {

  public void lernen(boolean yes) throws PruefungsPanikException {
    if (!yes)
      throw new PruefungsPanikException();
  }

}
