
public class Main {

  public static void main(String[] args) {

    // 1. Statische Methoden werden mit [Klassenname].[Methodenname] aufgerufen.

    Buch b1 = new Buch(19.99, 0.4);
    Milch m1 = new Milch(1.78, 0.5);

    double sum = IBuyable.calculateSum(b1, m1);
    double weight = IBuyable.calculateWeight(b1, m1);

    System.out.println("Sum: " + sum + " Weight: " + weight);

    // 2.
    NichtInstanziierbar.printInfo();

    // 3.
    MichGibtEsNurEinmal instance = MichGibtEsNurEinmal.getInstance();

  }

}
