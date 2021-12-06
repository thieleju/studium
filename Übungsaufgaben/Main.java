package Übungsaufgaben;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Person p1 = new Person(22, "Max", 2290.0, 'm', false);
    // p1.printInfo();

    Aufgabe4 a4 = new Aufgabe4();
    // a.print1();
    // a.print2();

    Aufgabe5 a5 = new Aufgabe5();
    // a5.print1();
    // a5.print2();
    // a5.print3();

    Aufgabe6 a6 = new Aufgabe6();
    // a6.printAplphabet();
    // a6.beispiel1();
    // a6.beispiel2();
    // a6.beispiel3();
    // a6.print3();
    // a6.print4();
    // a6.print5(124);
    // a6.print6(124);
    // a6.print7(5);
    // a6.print8(5);

    Aufgabe7 a7 = new Aufgabe7();
    

  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    s.close();
    return input;
  }
}