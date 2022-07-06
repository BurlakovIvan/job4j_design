package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Нет такого ключа");
        }
        return values.get(key);
    }

    private void validate(String str, int index) {
        if (index < 2
                || index == str.length() - 1
                || !str.startsWith("-")) {
            throw new IllegalArgumentException("Строка "
                    + str + " не соответствует шаблону -key=value");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new NoSuchElementException("Не передано никаких параметров");
        }
        for (String str : args) {
            var indexEq = str.indexOf("=");
            validate(str, indexEq);
            var key = str.substring(1, indexEq);
            var value = str.substring(indexEq + 1);
            values.putIfAbsent(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
