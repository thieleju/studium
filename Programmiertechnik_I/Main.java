package Programmiertechnik_I;

import java.util.Scanner;

import Programmiertechnik_I.Aufgabe10.Aufgabe10;
import Programmiertechnik_I.Aufgabe11.Aufgabe11;
import Programmiertechnik_I.Aufgabe12.Aufgabe12;
import Programmiertechnik_I.Aufgabe9.Aufgabe9;

public class Main {

  public static void main(String[] args) {

    Person p1 = new Person(22, "Max", 2290.0, 'm', false);

    Aufgabe4 a4 = new Aufgabe4();
    Aufgabe5 a5 = new Aufgabe5();
    Aufgabe6 a6 = new Aufgabe6();
    Aufgabe7 a7 = new Aufgabe7();
    Aufgabe8 a8 = new Aufgabe8();
    Aufgabe9 a9 = new Aufgabe9();
    Aufgabe10 a10 = new Aufgabe10();
    Aufgabe11 a11 = new Aufgabe11();
    Aufgabe12 a12 = new Aufgabe12();

    a12.print();

  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    s.close();
    return input;
  }
}