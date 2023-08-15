package src;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Beinhaltet die main-Methode und startet den ATM.
 */
public class Main {

  /**
   * Versucht ein atm-Objekt zu initialisieren und zu starten.
   * Fängt verschiedene Exceptions ab.
   * 
   * @param args
   */
  public static void main(String[] args) {

    try {
      ATM atm = ATM.getInstance(true, null);
      atm.start();

    } catch (FileNotFoundException fnfe) {
      System.out.println("Datei wurde nicht gefunden!");
      System.exit(1);

    } catch (IOException ioe) {
      System.out.println("Fehler beim Lesen einer Datei!");
      System.exit(1);

    } catch (Exception e) {
      System.out.println("Fehler: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
