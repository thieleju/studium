package Hello_World;

public class Aufgabe44 {

  public void print() {
    int x = 1;
    int y = 10;

    System.out.println(x); // 1
    x += 1;
    System.out.println(x); // 2
    y -= 1;
    System.out.println(y); // 9
    System.out.println(y); // 9
    y -= 1;
    System.out.println(y); // 8
    x -= 1;
    System.out.println(x + y); // 9
    y -= 1;
    System.out.println(y - x); // 6
    x += 1;
    System.out.println(x); // 2
    System.out.println(y); // 7
  }

}
