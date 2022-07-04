package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.valueSize(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithIllegalArgument() {
        String path = "./data/pair_with_Illegal_Argument.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutCommentSeveralEquals() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("age"), is("28=32"));
        assertThat(config.value("weight"), is("40="));
    }
}