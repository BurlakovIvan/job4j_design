package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        T rsl;
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
            rsl = out.pop();
        } else {
            rsl = out.pop();
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }
}
