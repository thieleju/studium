public class Main {

  public static void main(String[] args) {

    LinkedList list = new LinkedList(new Person("A"));
    list.addToFront(new Person("B"));
    list.addToFront(new Person("C"));

    list.printList();

    list.removeFirst();

    list.printList();
  }

}
