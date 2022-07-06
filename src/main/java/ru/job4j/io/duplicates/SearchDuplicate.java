package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchDuplicate {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        FileSearchDuplicate file = new FileSearchDuplicate();
        Files.walkFileTree(start, file);
        file.printFileDuplicates();
    }
}
