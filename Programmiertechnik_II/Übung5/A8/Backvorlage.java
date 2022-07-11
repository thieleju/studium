package A8;

public abstract class Backvorlage {

  public final void kuchenBacken() {
    zutatenBereitstellen();
    vermengen();
    backen();
  }

  public final void zutatenBereitstellen() {
    System.out.println("Stellen Sie die Zutaten bereit.");
  }

  abstract void vermengen();

  public final void backen() {
    System.out.println("Geben Sie den Kuchen in den Backofen.");
  }

}
