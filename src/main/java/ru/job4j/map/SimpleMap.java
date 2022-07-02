package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        var rsl = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        var index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 8);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        for (MapEntry<K, V> item : table) {
            if (item != null) {
                tableNew[indexFor(hash(item.key.hashCode()))] = item;
            }
        }
        modCount++;
        table = tableNew;
    }

    @Override
    public V get(K key) {
        var tableValue = table[indexFor(hash(key.hashCode()))];
        return (tableValue == null
                || (hash(tableValue.key.hashCode()) != hash(key.hashCode())
                || !tableValue.key.equals(key)))
                ? null : tableValue.value;
    }

    @Override
    public boolean remove(K key) {
        var index = indexFor(hash(key.hashCode()));
        var rsl = false;
        if (table[index] != null
                && hash(table[index].key.hashCode()) == hash(key.hashCode())
                && table[index].key.equals(key)) {
            table[index].value = null;
            table[index].key = null;
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;

            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                var rsl = index < count;
                while (rsl && table[index] == null) {
                    index++;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }

}
