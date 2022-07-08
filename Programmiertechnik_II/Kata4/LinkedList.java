public class LinkedList {

  private Node head;

  public LinkedList() {
    head = null;
  }

  public LinkedList(Person p) {
    head = new Node(p);
  }

  public void addToFront(Person p) {
    head = new Node(p, head);
  }

  public Person removeFirst() {
    Person p = head.getPerson();
    head = head.getNext();
    return p;
  }

  public int getSize() {
    int size = 0;
    Node current = head;
    while (current != null) {
      size++;
      current = current.getNext();
    }
    return size;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void addLast(Person p) {
    if (head == null)
      head = new Node(p);
    else {
      Node i = head;
      while (i.getNext() != null) {
        i = i.getNext();
      }
      i.setNext(new Node(p));
    }
  }

  public void printList() {
    Node current = head;
    while (current != null) {
      System.out.println(current.getPerson().getName());
      current = current.getNext();
    }
  }

  class Node {
    public Node next;
    public Person element;

    public Node(Person element) {
      this.element = element;
    }

    public Node(Person element, Node next) {
      this(element);
      this.next = next;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Node getNext() {
      return next;
    }

    public Person getPerson() {
      return element;
    }
  }
}
