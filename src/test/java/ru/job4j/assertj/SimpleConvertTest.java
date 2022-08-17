package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .anySatisfy(s -> assertThat(s).contains("first"))
                .allMatch(s -> s.length() > 3)
                .noneMatch(s -> s.startsWith("a"))
                .contains("second")
                .endsWith("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> list = simpleConvert.toSet("first", "second", "three", "four", "first");
        assertThat(list)
                .contains("first")
                .hasSize(4)
                .containsOnly("first",  "three", "four", "second");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> list = simpleConvert.toMap("first",  "second", "three", "four", "first");
        assertThat(list)
                .hasSize(4)
                .containsKeys("first",  "second", "three", "four")
                .containsValues(1, 2)
                .doesNotContainKey("ten")
                .doesNotContainValue(8)
                .containsEntry("four", 3);
    }
}
