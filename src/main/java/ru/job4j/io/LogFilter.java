package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            rsl = in
                   .lines()
                   .collect(Collectors.toMap(k -> k, v -> v.split(" ")))
                    .entrySet()
                    .stream()
                    .filter(v -> v.getValue().length > 1
                            && "404".equals(v.getValue()[v.getValue().length - 2]))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
