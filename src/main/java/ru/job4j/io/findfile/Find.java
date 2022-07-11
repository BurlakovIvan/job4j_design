package ru.job4j.io.findfile;

import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    private static void validation(String directory, String out) {
        File file = new File(directory);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Не существует %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Не является директорией %s", file.getAbsoluteFile()));
        }
        if (out == null || !out.contains(".")) {
            throw new IllegalArgumentException("Выходной файл имеет некорректное имя: " + out);
        }
    }

    private static boolean findMask(String fileName, String maskName) {
        Pattern mask = Pattern.compile('^' + maskName.replace(".", "[.]")
                .replace("*", ".*").replace("?", ".") + '$');
        Matcher matcher = mask.matcher(fileName);
        return matcher.find();
    }

    private static Predicate<Path> predicate(String type, String name) {
        Predicate<Path> pr;
        if ("mask".equals(type)) {
            pr = p -> findMask(p.toFile().getName(), name);
        } else if ("name".equals(type)) {
            pr = p -> p.toFile().getName().equals(name);
        } else if ("regex".equals(type)) {
            pr = p -> p.toFile().getName().matches(name);
        } else {
            throw new IllegalArgumentException("Не известный тип поиска: " + type);
        }
        return pr;
    }

    private static List<Path> search(String directory, String nameFile, String type) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate(type, nameFile));
        Files.walkFileTree(Path.of(directory), searcher);
        return searcher.getPaths();
    }

    private static void record(List<Path> paths, String out) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(out, Charset.forName("Windows-1251"), false))) {
            paths.forEach(pw::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
            if (args.length != 4) {
                throw new NoSuchElementException("Не корректное число входящих параметров. "
                        + "Не соответствуют шаблону -d=c:/ -n=*.txt -t=mask -o=*.txt");
            }
            ArgsName jvm = ArgsName.of(args);
            var directory = jvm.get("d");
            var fileResult = jvm.get("o");
            validation(directory, fileResult);
            var nameFileSearch = jvm.get("n");
            var type = jvm.get("t");
            var result = search(directory, nameFileSearch, type);
            if (result.size() == 0) {
                throw new FileNotFoundException("Не найдено ни одного файла");
            }
            record(result, fileResult);
    }
}
