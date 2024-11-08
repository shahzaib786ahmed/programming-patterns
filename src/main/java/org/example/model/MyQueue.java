package org.example.model;

import java.util.NoSuchElementException;

public class MyQueue<E> {
    private Node head;
    private Node tail;
    private int size;

    public boolean add(E value) {
        Node newNode = new Node(value);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty.");
        }

        E value = head.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return value;
    }

    public E peek() {
        return head.value;
    }

    private class Node {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
        }
    }
}
