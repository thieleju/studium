package Programmiertechnik_II.Workbook2;

public interface IBuyable {

  public abstract double getPrice();

  public abstract double getWeight();

  public static double calculateSum(IBuyable... buyables) {
    double sum = 0;
    for (IBuyable buyable : buyables) {
      sum += buyable.getPrice();
    }
    return sum;
  }

  public static double calculateWeight(IBuyable... buyables) {
    double weight = 0;
    for (IBuyable buyable : buyables) {
      weight += buyable.getWeight();
    }
    return weight;
  }
}