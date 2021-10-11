package Hello_World;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    System.out.println("hello " + getInput());
  }

  public static String getInput() {
    Scanner s = new Scanner(System.in);
    return s.nextLine();
  }
}
