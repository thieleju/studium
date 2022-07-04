public class Main {
    
    public static void main(String[] args) {
        
        Queue<Person> q = new Queue<>(new Person("Anton"));

        q.enqueue(new Person("Berta"));
        q.enqueue(new Person("Carsten"));
        q.enqueue(new Person("Dobbi"));
        q.enqueue(new Person("Emma"));

        q.printQueue();

        q.dequeue();
        q.dequeue();

        q.printQueue();

        // Test with Integer
        Queue<Integer> q2 = new Queue<>(1);
        q2.enqueue(2);
        q2.enqueue(3);
        q2.enqueue(4);
        q2.enqueue(5);
        q2.printQueue();

    }

}
