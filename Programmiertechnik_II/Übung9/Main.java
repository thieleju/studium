public class Main {

  public static void main(String[] args) {

    // 1
    int i = 42;
    Integer wrapped = i;

    i++;
    wrapped++;

    machWas(i);
    machWas(wrapped);
    machWasAnderes(i);
    machWasAnderes(wrapped);

    Boolean bEingepackt = true;
    Boolean bUnverpackt = false;

    if ((bEingepackt == true) | (bUnverpackt == true)) {
      System.out.println(bEingepackt);
      System.out.println(bUnverpackt);
    }

    WrapperKlasse2 w = new WrapperKlasse2();
    w.machWas();

  }

  public static void machWas(Integer i) {
    System.out.println(i);
  }

  public static void machWasAnderes(int i) {
    System.out.println(i);
  }
}
