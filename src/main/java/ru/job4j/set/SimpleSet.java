package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        var exist = contains(value);
        if (!exist) {
           set.add(value);
        }
        return !exist;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> iterator = set.iterator();
        var rsl = false;
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
