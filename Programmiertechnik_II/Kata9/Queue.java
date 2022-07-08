public class Queue<T> {

  private Node<T> head;

  public Queue(T p) {
    head = new Node<T>(p);
  }

  class Node<T> {
    private T current;
    private Node<T> next;

    public Node(T current) {
      this.current = current;
    }

    public Node<T> getNext() {
      return next;
    }

    public void setNext(Node<T> next) {
      this.next = next;
    }

    public T getCurrent() {
      return current;
    }
  }

  public void enqueue(T p) {
    Node<T> i = head;
    while (i.getNext() != null) {
      i = i.getNext();
    }
    i.setNext(new Node<T>(p));
  }

  public void dequeue() {
    head = head.getNext();
  }

  public T peek() {
    return head.getCurrent();
  }

  public void printQueue() {
    System.out.println("Queue:");
    Node<T> i = head;
    System.out.print(i.getCurrent());
    int counter = 1;
    while (i.getNext() != null) {
      i = i.getNext();
      System.out.print(i.getCurrent());
      counter++;
    }
    System.out.println("\nQueue length: " + counter);
  }
}