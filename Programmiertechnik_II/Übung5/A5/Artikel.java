package A5;

public abstract class Artikel implements IBuyable {

  private double price;
  private double weight;
  private Lieferstatus status;

  public double getPrice() {
    return price;
  }

  public Lieferstatus getLieferstatus() {
    return status;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}
