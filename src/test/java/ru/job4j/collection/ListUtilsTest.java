package ru.job4j.collection;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterBeginner() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 3);
        assertThat(input, is(Arrays.asList(0, 3, 1, 2)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveIfMoreTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(4, 8, 1, 0, 3));
        ListUtils.removeIf(input, n -> n > 2);
        assertThat(input, is(Arrays.asList(1, 0)));
    }

    @Test
    public void whenReplaceIfEqualsOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 8, 1, 0, 1));
        ListUtils.replaceIf(input, n -> n == 1, 78);
        assertThat(input, is(Arrays.asList(78, 8, 78, 0, 78)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 4, 0, 1));
        List<Integer> deleteList = new ArrayList<>(Arrays.asList(1, 0));
        ListUtils.removeAll(input, deleteList);
        assertThat(input, is(Arrays.asList(2, 3, 4)));
    }
}