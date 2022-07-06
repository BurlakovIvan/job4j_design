package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSearchDuplicate extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> paths = new HashMap<>();

    public void printFileDuplicates() {
        paths
                .values()
                .stream()
                .filter(p -> p.size() > 1)
                .forEach(System.out::println);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (!attrs.isDirectory()) {
            FileProperty newFile = new FileProperty(attrs.size(), file.toFile().getName());
            paths.putIfAbsent(newFile, new ArrayList<>());
            paths.get(newFile).add(file);
        }
        return FileVisitResult.CONTINUE;
    }

}
