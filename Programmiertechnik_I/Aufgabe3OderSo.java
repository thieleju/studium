package Programmiertechnik_I;

public class Aufgabe3OderSo {

  // find the prompt
  public void print() {
    int x = 0;
    int y = 0;

    while (x < 5) {

      if (y < 5) {
        x = x + 1;
        if (y < 3) {
          x = x - 1;
        }
      }
      y = y + 2;

      System.out.print(x + "" + y + " ");
      x = x + 1;
    }
  }
}
