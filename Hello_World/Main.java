package Hello_World;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    // System.out.println("Please enter your name:");
    // System.out.println("hello " + getInput());

    Person p1 = new Person(22, "Max", 2290.0, 'm', false);
    p1.printInfo();

    Aufgabe42 a = new Aufgabe42();
    a.print();

    Aufgabe44 a2 = new Aufgabe44();
    a2.print();
  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();
    s.close();
    return input;
  }
}