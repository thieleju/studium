package sonstiges.GenericTypesTest;

public class Main {

  public static void main(String[] args) {

    Dog dog = new Dog("good boy", 1);
    Cat cat = new Cat("grumpy cat", 2);

    Zoo<Dog, Cat> z = new Zoo<>(dog, cat);

    z.makeSound();

    System.out.println(z.toString());
    System.out.println(z.isSameType());

  }
}