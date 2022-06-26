package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> head;

    private int size;

    private int modCount;

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        if (head == null) {
            this.head = new Node<>(value);
        } else {
            Node<E> headTemp = head;
            while (headTemp.getNext() != null) {
                headTemp = headTemp.getNext();
            }
            headTemp.setNext(new Node<>(value));
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        var currentIndex = 0;
        var headTemp = head;
        while (headTemp != null) {
            if (currentIndex == index) {
               return headTemp.getValue();
           } else {
                headTemp = headTemp.getNext();
                currentIndex++;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;

            private Node<E> currentIterationNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIterationNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var rsl = currentIterationNode.getValue();
                currentIterationNode = currentIterationNode.getNext();
                return rsl;
            }

        };
    }
}
