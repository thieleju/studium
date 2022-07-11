
public class Konto implements Cloneable {

  private int nummer;

  public Konto(int nummer) {
    this.nummer = nummer;
  }

  public int getNummer() {
    return nummer;
  }

  public void setNummer(int nummer) {
    this.nummer = nummer;
  }

  public String toString() {
    return "Konto " + nummer;
  }

  @Override
  public Konto clone() {
    Konto k = null;
    try {
      k = (Konto) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return k;
  }

}
