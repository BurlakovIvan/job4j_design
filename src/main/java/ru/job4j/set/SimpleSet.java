package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

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
            var num = iterator.next();
            if ((num == null && value == null)
                    || (num != null && num.equals(value))) {
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
