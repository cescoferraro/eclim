package main.stack;

public class Stack {

    private Node start;
	private int count;

    public class Node {

        public Node next;
        public int element;

        public Node(int element) {
            next = null;
            this.element = element;
        }
    }

    public Stack() {
        start = null;
    }

    public boolean isEmpty() {
        return start == null;
    }

	public int size(){
		return count;
	}
	public int peek(){
		return start.element;
	}

    public void add(int e) {
        Node aux = new Node(e);
        aux.next = start;
        start = aux;
		count++;
    }

    public int pop() {
        int r = -1;

        if (isEmpty()) {
            System.err.println("Erro! A pilha está isEmpty.");
        } else {
            r = start.element;
            start = start.next;
			count--;
        }

        return r;
    }

}
