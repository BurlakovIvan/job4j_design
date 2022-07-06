package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        String pathStart = args[0];
        String fileExtension = args[1];
        Path start = Paths.get(pathStart);
        search(start, p -> p.toFile().getName().endsWith(fileExtension)).forEach(System.out::println);
    }

    private static void validation(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null");
        }
        if (args[0] == null || args[1] == null) {
            throw new NullPointerException("Null аргумент");
        }
        var index = args[0].indexOf(":\\");
        if (index < 0) {
            throw new IllegalArgumentException("Wrong first argument");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong second argument");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
