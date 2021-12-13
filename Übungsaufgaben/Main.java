package Übungsaufgaben;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Person p1 = new Person(22, "Max", 2290.0, 'm', false);

    Aufgabe4 a4 = new Aufgabe4();
    Aufgabe5 a5 = new Aufgabe5();
    Aufgabe6 a6 = new Aufgabe6();
    Aufgabe7 a7 = new Aufgabe7();
    Aufgabe8 a8 = new Aufgabe8();
    int[] array = { 1, 2, 3, 4 };

    a8.print81(array);
    // a8.print81(a8.arrayCopy(array));
    a8.print81(a8.rotate(array, 1, false));
    a8.print81(a8.rotate(array, 1, true));
    a8.zahlWuerfeln(6);
    a8.doppelteZahlenWürfeln(6);
    a8.wortgruppen();

    int[] unsorted = { 4, 6, 1, 7, 9, 25, 23 };
    a8.print81(a8.bubbleSort(unsorted));
  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    s.close();
    return input;
  }
}