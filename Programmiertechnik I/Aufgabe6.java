package Übungsaufgaben;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aufgabe6 {

  // Aufgabe 6.1
  // initialize function which prints the alphabet
  public void printAplphabet() {
    char start = 'A';
    for (int i = 0; i < 26; i++) {
      System.out.print(start);
      start++;
    }
    System.out.println();
  }

  // Aufgabe 6.2
  // Reparatur: x inkrementieren / Decrementierung nach unten verschieben
  public void beispiel1() {
    int x = 1;
    while (x < 10) {
      if (x > 5) {
        System.out.println("Ich bin verwirrt. " + x);
      }
    }
  }

  public void beispiel2() {
    int x = 5;
    while (x > 1) {
      x = x - 1;
      if (x < 3) {
        System.out.println("Ich bin immer noch verwirrt... " + x);
      }
    }
  }

  public void beispiel3() {
    int x = 5;
    while (x > 2) {
      x = x - 1;
      if (x < 3) {
        System.out.println("Jetzt mag ich nicht mehr " + x);
      }
    }
  }

  // Aufgabe 6.3
  // Gibt alle geraden Zahlen von 10 bis 1 aus
  public void print3() {
    int i = 10;

    while (i > 0) {
      System.out.println(i);
      i -= 2;
    }

    i = 10;
    do {
      System.out.println(i);
      i -= 2;
    } while (i > 0);

    for (i = 10; i > 0; i -= 2) {
      System.out.println(i);
    }
  }

  // Aufgabe 6.4
  // gibt alle Zahlen aus, die durch 9 teilbar sind und nicht 50 sind
  public void print4() {
    for (int i = 0; i < 100; i++) {
      if (i == 50) {
        break;
      }
      if (i % 9 != 0) {
        continue;
      }
      System.out.println(i);
    }

    for (int i = 0; i < 50; i++) {
      if (i % 9 == 0) {
        System.out.println(i);
      }
    }
    // Print recursively
    recursiveFunction(0, 100);
  }

  public Integer recursiveFunction(int counter, int max) {
    if (counter == max || counter == 50) {
      return 0;
    }
    if (counter % 9 == 0) {
      System.out.println(counter);
    }
    return counter + recursiveFunction(counter + 1, max);
  }

  // Aufgabe 6.5
  public void print5(int number) {
    // 124 / 10 = 12 R 4
    // sum = sum + R -> sum = 4
    // 12 / 10 = 1 R 2
    // sum = 4 + 2 -> sum = 6
    // 1 / 10 = 0 R 1
    // sum = 6 + 1 -> sum = 7
    int sum = 0;
    while (number > 0) {
      sum += number % 10;
      number /= 10;
    }
    System.out.println(sum);
  }

  // Aufgabe 6.6
  // initialize a function that prints the given number as word strings
  public void print6(int number) {
    String[] words = { "null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun" };
    // not necessary, alternatively print word inside while loop
    ArrayList<String> finished = new ArrayList<String>();

    while (number > 0) {
      String word = words[number % 10];
      finished.add(word);
      number /= 10;
    }
    Collections.reverse(finished);
    System.out.println(finished);
  }

  // Aufgabe 6.7
  // print triangle in console
  public void print7(int height) {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j <= i; j++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

  // Aufgabe 6.8
  // print christmas tree of height n with stump in the middle
  public void print8(int height) {
    int doubleHeight = 2 * height;
    for (int i = 0; i < doubleHeight; i++) {
      // only odd lines
      if (i % 2 == 0) {
        // Spaces before
        for (int j = 0; j < doubleHeight - i; j++) {
          if (j % 2 == 0)
            System.out.print(" ");
        }
        // body *
        for (int j = 0; j <= i; j++) {
          System.out.print("*");
        }
        System.out.println();
      }
    }
    // print tree stump
    for (int i = 0; i < height; i++)
      System.out.print(" ");
    System.out.println("|");
  }

  // public void printMultiply() {
  // // initialize list of doubles
  // double[] list = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
  // System.out.println(multiplyAll(list));
  // }

  // public double multiplyAll(double[] arguments) {
  // int result = 1;
  // // for (int i = 0; i < arguments.length; i++) {
  // // result *= arguments[i];
  // // }
  // for (double el : arguments) {
  // result *= el;
  // }
  // return result;
  // }

}
