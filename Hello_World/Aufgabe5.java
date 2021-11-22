package Hello_World;

public class Aufgabe5 {

  public void print1() {
    char a = 'A';
    char b = 'B';

    if (a == --b) {
      System.out.println("1");
    }

    if ((a == b) || (a++ > b)) {
      System.out.println("2");
    }

    System.out.println(a);
    // A
  }

  public void print2() {
    // Variante I
    int testInt = 31 / 7; // 4
    float testFloat = 31 / 7; // 4,00
    double testDouble = 31 / 7; // 4,00
    System.out.println("testInt" + " " + testInt);
    System.out.println("testFloat" + " " + testFloat);
    System.out.println("testDouble" + " " + testDouble);

    // Variante II
    int testInt2 = 31 / 3; // 10
    float testFloat2 = 31f / 3f; // 10,33
    double testDouble2 = 31d / 3d; // 10,33
    System.out.println("testInt" + " " + testInt2);
    System.out.println("testFloat2" + " " + testFloat2);
    System.out.println("testDouble2" + " " + testDouble2);
  }

  public void print3() {
    int x = 1, y = 2, z = 3;
    System.out.println(x--); // 1
    System.out.println(++x - x); // 1-1 = 0
    System.out.println(x); // 1

    if (x++ == 0) {
      System.out.println(x); // no print
    }

    System.out.println(x); // 2

    if ((y == 2) || (x++ > 0)) {
      System.out.println(x); // x = 2; y = 2
    }

    System.out.println(x); // 2

    x += y; // x = 2 + 2 = 4

    System.out.println(x); // 4

    if ((y == 2) | (x++ > 0)) {
      System.out.println(x); // y = 2; x = 5
      z -= y;
    }

    System.out.println(x); // x = 5
    System.out.println(y); // y = 2
    System.out.println(z); // z = 3 - 2 = 1
  }

  // KWR KISS
  // 1 Lexer
  // 2 Referenzdaten
  // 3 Initialisierung
  // 4 Wertebereich
  // 5 Integer
  // 6 Deklaration
  // 7 Konstante
  // 8 Schluesselwort
  // 9 Casting
  // 10 Modulo
  // 11 main
  // 12 Kurzschlussoperatoren
  // 13 Abstraktion
  // 14 Bug
}
