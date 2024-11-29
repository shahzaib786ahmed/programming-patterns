package org.example.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Appends a new value at the tail of the queue
     *
     * @param value the value of the new element
     * @return if the element is appended successfully
     */
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

    /**
     * gets the element of the head
     *
     * @return the value of the head element
     */
    public E peek() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        return head.value;
    }

    /**
     * removes an element of the head
     *
     * @return the value of the removed head element
     */
    public E poll() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        E value = head.value;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return value;
    }

    /**
     * Prints the string of the queue
     *
     * @return the string
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node current = head;

        while (current.next != null) {
            sb.append(current.value).append(", ");
            current = current.next;
        }
        sb.append(current.value).append("]");
        return sb.toString();
    }

    /**
     * Iterates through the queue
     *
     * @return the iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    /**
     * Represents a node in a linked list.
     * This class holds a value of type E and a reference to the next node in the list.
     * The node is used as a building block for creating linked data structures.
     */
    private class Node {
        E value;
        Node next;

        /**
         * Constructor to create a new node with a specified value.
         * The next reference is initialized to null by default.
         *
         * @param value the value to be stored in this node.
         */
        public Node(E value) {
            this.value = value;
        }
    }
}
