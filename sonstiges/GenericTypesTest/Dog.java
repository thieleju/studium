public class Dog extends Animal {

  public Dog(String name, int age) {
    super(name, age, "Dog");
  }

  public void makeSound() {
    System.out.println("*woof woof*");
  }

}
