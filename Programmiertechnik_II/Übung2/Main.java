
public class Main {

  public static void main(String[] args) {

    // Aufgabe 1 "TurboLernenFuerPruefungenTestlauf.java"
    TurboLernenFuerPruefungen tlfp = new TurboLernenFuerPruefungen();
    try {
      tlfp.lernen(true);
      tlfp.lernen(false);

    } catch (PruefungsPanik ppe) {
      ppe.printStackTrace();
    }

    // Aufgabe 2
    // Exception <- SeineException <- IhreException <- DeineException <-
    // MeineException

    // Aufgabe 3

    // Aufgabe 4
    try {
      Hund hund = new Hund("Wolf", 10);

      System.out.println(hund);

      hund.setAlter(-12);

      System.out.println(hund);

    } catch (InvalidDogAgeException ide) {
      System.out.println("InvalidDogAgeException: " + ide.getMessage());

    } catch (Exception e) {
      System.out.println("Normal Exception from Aufgabe 4");
      e.printStackTrace();
    }

  }
}
