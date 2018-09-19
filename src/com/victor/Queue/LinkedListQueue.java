package com.victor.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, rear;    // head(rear) point to the real element at head(rear) in queue
    private int size;

    public LinkedListQueue() {
        head = null;
        rear = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (rear == null) {
            rear = new Node(e);
            head = rear;
        } else {
            rear.next = new Node(e);
            rear = rear.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null)
            rear = head;
        size--;

        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot get front from an empty queue.");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front->");
        for (Node cur = head; cur != null; cur = cur.next) {
            res.append(cur + "-> ");
        }
        res.append("null <-rear");

        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }
}
