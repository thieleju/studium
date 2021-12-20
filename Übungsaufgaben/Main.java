package Übungsaufgaben;

import java.util.Scanner;

import Übungsaufgaben.Aufgabe9.Aufgabe9;
import Übungsaufgaben.Aufgabe9.Buch;
import Übungsaufgaben.Aufgabe9.Mitarbeiter;
import Übungsaufgaben.Aufgabe9.SchachSpieler;

public class Main {

  public static void main(String[] args) {

    Person p1 = new Person(22, "Max", 2290.0, 'm', false);

    Aufgabe4 a4 = new Aufgabe4();
    Aufgabe5 a5 = new Aufgabe5();
    Aufgabe6 a6 = new Aufgabe6();
    Aufgabe7 a7 = new Aufgabe7();
    Aufgabe8 a8 = new Aufgabe8();
    // a8.print(a8);

    Aufgabe9 a9 = new Aufgabe9();
    a9.print();

  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    s.close();
    return input;
  }
}