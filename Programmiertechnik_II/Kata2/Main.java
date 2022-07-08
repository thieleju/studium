public class Main {

  public static void main(String[] args) {

    TurboLernenFuerPruefungen turbo = new TurboLernenFuerPruefungen();
    try {
      turbo.lernen(true);
      turbo.lernen(false);

    } catch (PruefungsPanikException e) {
      System.out.println(e.getMessage());
    }
  }

}
