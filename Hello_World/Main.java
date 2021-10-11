package Hello_World;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    String name = s.nextLine();

    System.out.println("hello " + name);
  }
}
