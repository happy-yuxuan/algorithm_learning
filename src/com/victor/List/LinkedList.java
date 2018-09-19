package com.victor.List;


public class LinkedList<E> {

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

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public LinkedList(E[] arr) {
        if (arr == null) throw new IllegalArgumentException("arr cannot be null!");

        dummyHead = new Node(null, null);
        Node rear = dummyHead;
        for (int i = 0; i < arr.length; i++) {
            rear.next = new Node(arr[i]);
            rear = rear.next;
        }
        rear.next = null;

        size = arr.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // add element at index(0~based)
    // is not a operation in common use,More inclined to practice :)
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        prev.next = new Node(e, prev.next);

        size++;
    }

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Set failed. Illegal index.");

        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }

        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Remove failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // remove all element equal to val from linked list
    public void removeElement(E val) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(val)) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null; // loitering Object
            } else {
                prev = prev.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "-> ");
        }
        res.append("null");

        return res.toString();
    }



    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println("--------------");
        Integer[] nums = {1, 2, 6, 3, 4, 5, 6};
        LinkedList<Integer> linkedList1 = new LinkedList<>(nums);
        linkedList1.removeElement(6);
        System.out.println(linkedList1);
    }

}
