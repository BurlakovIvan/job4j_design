package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SimpleMapTest {

    @Test
    public void whenPutInMap() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put("first", 1));
        assertTrue(simpleMap.put("second", 2));
        assertFalse(simpleMap.put("first", 1));
    }

    @Test
    public void whenPutInMapMoreEight() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        simpleMap.put("2", 6);
        simpleMap.put("3", 7);
        simpleMap.put("4", 8);
        simpleMap.put("5", 9);
        simpleMap.put("6", 7);
        simpleMap.put("7", 8);
        simpleMap.put("8", 9);
        assertThat(simpleMap.get("6"), is(7));
    }

    @Test
    public void whenGetForSevenEqualsEight() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        simpleMap.put("7", 8);
        simpleMap.put("8", 9);
        assertThat(simpleMap.get("7"), is(8));
    }

    @Test
    public void whenGetForSevenNull() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        simpleMap.put("7", 8);
        simpleMap.put("8", 9);
        assertNull(simpleMap.get("2"));
    }

    @Test
    public void removeKeyNotExist() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        simpleMap.put("2", 4);
        simpleMap.put("8", 9);
        assertFalse(simpleMap.remove("3"));
    }

    @Test
    public void removeKeyTwo() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        simpleMap.put("2", 4);
        simpleMap.put("8", 9);
        assertTrue(simpleMap.remove("2"));
        assertNull(simpleMap.get("2"));
    }

    @Test
    public void iterator() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        simpleMap.put("1", 2);
        Iterator<String> iterator = simpleMap.iterator();
        iterator.hasNext();
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionNoSuchElement() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        Iterator<String> iterator = simpleMap.iterator();
        iterator.hasNext();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorExceptionConcurrentModification() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("0", 1);
        Iterator<String> iterator = simpleMap.iterator();
        iterator.hasNext();
        iterator.next();
        simpleMap.put("1", 2);
        iterator.hasNext();
    }
}