package Aufgabe12;

public class VerlagBuchTest {

  public static void machZeugMitBuch(Buch b) {
    Buch b1 = new Buch(b.getVerlag());
    b1.setVerlag(null);
  }

}
