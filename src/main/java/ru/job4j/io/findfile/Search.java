package ru.job4j.io.findfile;

import ru.job4j.io.SearchFiles;

import java.io.File;
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

    public static void validation(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong type files '" + args[1] + "'");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
