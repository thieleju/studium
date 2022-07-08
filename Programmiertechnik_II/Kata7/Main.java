import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {

    ArrayList<Paket> pakete = new ArrayList<Paket>();

    pakete.add(new Paket("E", 5.0, 1.0));
    pakete.add(new Paket("C", 3.0, 1.0));
    pakete.add(new Paket("B", 2.0, 1.0));
    pakete.add(new Paket("D", 4.0, 1.0));
    pakete.add(new Paket("F", 6.0, 1.0));
    pakete.add(new Paket("A", 1.0, 1.0));

    for (Paket p : pakete) {
      System.out.println(p.getAbsender() + " " + p.getGewicht() + " " + p.getGroesse());
    }

    System.out.println("-----");

    pakete.sort(null);

    for (Paket p : pakete) {
      System.out.println(p.getAbsender() + " " + p.getGewicht() + " " + p.getGroesse());
    }

  }

}
