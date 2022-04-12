package sonstiges.GenericTypesTest;

public class Cat extends Animal {

  public Cat(String name, int age) {
    super(name, age, "Cat");
  }

  public void makeSound() {
    System.out.println("*meow*");
  }

}
