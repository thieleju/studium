package Programmiertechnik_I;

public class Aufgabe4 {

  public void print1() {
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

  public void print2() {
    int x = 1;
    int y = 10;

    System.out.println(x); // 1
    x += 1;
    System.out.println(x); // 2
    y -= 1;
    System.out.println(y); // 9
    System.out.println(y); // 9
    y -= 1;
    System.out.println(y); // 8
    x -= 1;
    System.out.println(x + y); // 9
    y -= 1;
    System.out.println(y - x); // 6
    x += 1;
    System.out.println(x); // 2
    System.out.println(y); // 7
  }
}
