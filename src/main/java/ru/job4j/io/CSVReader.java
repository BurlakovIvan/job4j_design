package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private static void validation(Path path, String out) {
        validationFile(path);
        if (!"stdout".equals(out) && !out.contains(".")) {
            throw new IllegalArgumentException("Output file have an incorrect name: " + out);
        }
    }

    private static void validationFile(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exist %s", path));
        }
        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not file %s", path));
        }
        if (!path.toString().endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong type files '" + path + "'. Must be .csv");
        }
    }
    private static int indexColumn(List<String> column, String nameColumn) {
        int index = -1;
        if (nameColumn != null) {
            index = column.indexOf(nameColumn);
        }
        if (index < 0) {
            throw new IllegalArgumentException("Wrong filter. Not founded column with name: "
                    + nameColumn);
        }
        return index;
    }

    private static String outWriteArray(List<Integer> indexColumnList,
                                      String delimiter, List<String> str) {
        StringJoiner strResult = new StringJoiner(delimiter);
        for (int index : indexColumnList) {
            strResult.add(str.get(index));
        }
        return strResult.toString();
    }

    private static void outWrite(String out, List<String> result) {
        if ("stdout".equals(out)) {
            result.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(
                    new FileWriter(out, Charset.forName("Windows-1251"), true))) {
                result.forEach(pw::println);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        var path = Path.of(argsName.get("path"));
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var filter = argsName.get("filter");
        var columnName = filter.split(",");
        validation(path, out);
        var scanner = new Scanner(path).useDelimiter("\\n");
        List<Integer> indexColumnList = new ArrayList<>();
        List<String> outResult = new ArrayList<>();
        if (scanner.hasNext()) {
            List<String> column = Arrays.stream(scanner.next().split(delimiter)).toList();
            for (String str : columnName) {
                indexColumnList.add(indexColumn(column, str));
            }
            outResult.add(outWriteArray(indexColumnList, delimiter, column));
        }
        while (scanner.hasNext()) {
            var str = Arrays.stream(scanner.next().split(delimiter)).toList();
            outResult.add(outWriteArray(indexColumnList, delimiter, str));
        }
        outWrite(out, outResult);
    }
}