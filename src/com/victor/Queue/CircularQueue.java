package com.victor.Queue;

public class CircularQueue<E> implements Queue<E> {
    private E[] data;
    private int front, rear;
    private int size;

    public CircularQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = rear = 0;
        size = 0;
    }

    public CircularQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public void enqueue(E e) {
        if ((rear + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[rear] = e;
        rear = (rear + 1) % data.length;
        size++;
    }


    @Override
    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null; // loitering Object != memory leak
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new IllegalArgumentException("Cannot get front from an empty queue.");
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        rear = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d | ", size, getCapacity()));
        res.append("front-> [");
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            res.append(data[i]);
            if (i != rear - 1) {
                res.append(", ");
            }
        }
        res.append("] <-rear");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new CircularQueue<>();
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
