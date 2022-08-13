package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void checkArrayEmptyNUll() {
        NameLoad nameLoad = new NameLoad();
        String[] array = new String[10];
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void checkArrayNotSymbolEqual() {
        NameLoad nameLoad = new NameLoad();
        String arr = "ar";
        String[] array = new String[] {arr};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(arr)
                .hasMessageContaining("=");
    }

    @Test
    void checkArrayNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String arr = "ar=";
        String[] array = new String[] {arr};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(arr)
                .hasMessageContaining("value");
    }

    @Test
    void checkArrayNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String arr = "=ar";
        String[] array = new String[] {arr};
        assertThatThrownBy(() -> nameLoad.parse(array))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(arr)
                .hasMessageContaining("key");
    }

}