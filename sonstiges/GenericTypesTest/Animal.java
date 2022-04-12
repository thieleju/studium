package sonstiges.GenericTypesTest;

public class Animal {

  protected String name;
  protected int age;
  protected String type;

  protected Animal(String name, int age, String type) {
    this.name = name;
    this.age = age;
    this.type = type;
  }

  public void makeSound() {
    System.out.println("*Animal sound*");
  }

  public String toString() {
    return type + ": " + name + " is " + age + " years old";
  }

}
