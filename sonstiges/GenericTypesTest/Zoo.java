
public class Zoo<A1 extends Animal, A2 extends Animal> {
  private A1 animal1;
  private A2 animal2;

  public Zoo(A1 animal1, A2 animal2) {
    this.animal1 = animal1;
    this.animal2 = animal2;
  }

  public void makeSound() {
    animal1.makeSound();
    animal2.makeSound();
  }

  public Boolean isSameType() {
    return animal1.getClass().equals(animal2.getClass());
  }

  public String toString() {
    return animal1.toString() + "\n" + animal2.toString();
  }
}
