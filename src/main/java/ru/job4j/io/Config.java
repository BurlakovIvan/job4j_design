package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(s -> !s.startsWith("#")
                    && s.contains("="))
                    .toList()
                    .forEach(str -> {
                        var index = str.indexOf('=');
                        if (index == 0 || index == (str.length() - 1)) {
                            throw new IllegalArgumentException();
                        }
                        values.put(str.substring(0, index), str.substring(index + 1));
                    });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int valueSize() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}
