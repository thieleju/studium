package Hello_World;

public class Aufgabe42 {

  public void print() {
    char zahl = 77;
    char zeichen = 'M';

    // M
    System.out.println(zahl); // Ausgabe 1
    // M
    System.out.println(zeichen); // Ausgabe 2
    // 78
    System.out.println(zahl + 1); // Ausgabe 3
    // 78
    System.out.println(zeichen + 1); // Ausgabe 4

    zahl = (char) (zahl + 1);
    // N
    System.out.println(zahl); // Ausgabe 5

    zeichen = (char) (zeichen + 1);
    // N
    System.out.println(zeichen);
  }
}
