package Programmiertechnik_I.Aufgabe9;

public class Aufgabe9 {

  // copies an array
  public int[] arrayCopy(int[] a) {
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      b[i] = a[i];
    }
    return b;
  }

  /**
   * This function rotates the values of an array by n and in direction lef/right
   * 
   * @param array the array which should be rotated
   * @param n     the amount of rotations to one side
   * @param left  if true rotate left, if false rotate right
   * @return the new rotated array
   */
  public int[] rotate(int[] array, int n, boolean left) {
    // Create new copy of array
    int[] newArray = arrayCopy(array);
    // rotate to left or right by n
    for (int i = 0; i < array.length; i++) {
      // calculate new position
      int pos;
      if (left)
        pos = (i + n) % array.length;
      else
        pos = (i + array.length - n) % array.length;
      // copy value from old array at pos
      newArray[i] = array[pos];
    }
    return newArray;
  }

  // 9.2
  // Es wird ein Kalender für 1 Jahr initialisiert usw
  public void initJahresKalender() {
    String[][] jahresKalender = new String[12][];

    jahresKalender[0] = new String[31];
    jahresKalender[1] = new String[28];
    jahresKalender[2] = new String[31];
    jahresKalender[3] = new String[30];
    jahresKalender[4] = new String[31];
    jahresKalender[5] = new String[30];
    jahresKalender[6] = new String[31];
    jahresKalender[7] = new String[31];
    jahresKalender[8] = new String[30];
    jahresKalender[9] = new String[31];
    jahresKalender[10] = new String[30];
    jahresKalender[11] = new String[31];

    jahresKalender[0][0] = "Neujahr";
    jahresKalender[0][5] = "Heilige Drei Könige";
    jahresKalender[4][0] = "Tag der Arbeit";
  }

  public void print() {
    // 9.3 siehe Klasse "Buch"
    Buch b1 = new Buch();
    Buch b2 = new Buch();

    b1.setTitle("Hallo Welt");
    b1.setPreis(9.99);
    b1.setISBN("1234");

    System.out.println(b1.toSting());

    // 9.4
    // Erläutern Sie den Aufbau der Klasse (grafisch). Erstellen Sie hierfür ein
    // UML-Diagramm. Nop

    // Was passiert durch die Anweisungen? Warum benötigt man die zweite Anweisung?
    // SchachSpieler mueller; -> Deklaration von mueller
    // mueller = new SchachSpieler(); -> instanziierung von mueller

    // 9.4c
    SchachSpieler s1 = new SchachSpieler();
    s1.setName("Boris");
    s1.setAlter(21);
    SchachSpieler s2 = new SchachSpieler();
    s2.setName("Antonia");
    s2.setAlter(65);
    // 9.4d
    System.out.println(s1.getName() + " is " + s1.getAlter() + " Jahre alt");
    System.out.println(s2.getName() + " is " + s2.getAlter() + " Jahre alt");

    // 9.5 Test Mitarbeiter methods
    Mitarbeiter m1 = new Mitarbeiter();
    m1.setVorname("Julian");
    m1.setName("Thiele");
    m1.setAlter(23);
    System.out.println(m1.toString());

    Mitarbeiter m2 = new Mitarbeiter();
    m2.setVorname("Maximilian");
    m2.setName("Wolleb");
    m2.setAlter(22);
    System.out.println(m2.toString());

    if (m1.istAelter(m2)) {
      System.out.println(m1.getVorname() + " ist älter als " + m2.getVorname());
    } else {
      System.out.println(m1.getVorname() + " ist jünger als " + m2.getVorname());
    }

    // hc[0] -> null
    // hc[1] -> 1
    // hc[2] -> 2
    // hc[3] -> 2
    // hc[4] -> 0
  }

}
