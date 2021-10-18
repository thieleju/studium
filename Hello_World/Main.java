package Hello_World;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    System.out.println("Please enter your name:");
    System.out.println("hello " + getInput());
  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    String str = s.nextLine();
    s.close();
    return str;
  }
}
