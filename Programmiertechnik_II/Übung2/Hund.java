public class Hund {

  private String name;
  private int alter;

  public Hund(String name, int alter) throws InvalidDogAgeException {
    this.name = name;
    setAlter(alter);
  }

  public void setAlter(int alter) throws InvalidDogAgeException {
    if (alter <= 0)
      throw new InvalidDogAgeException(alter);
    this.alter = alter;
  }

  public int alterInPersonenJahren() {
    return alter * 7;
  }

  public String toString() {
    return "Name: " + name + ", Alter: " + alter + " Jahre" + ", Alter in Personenjahren: " + alterInPersonenJahren();
  }

  public String getName() {
    return name;
  }

  public int getAlter() {
    return alter;
  }

  public void setName(String name) {
    this.name = name;
  }

}
