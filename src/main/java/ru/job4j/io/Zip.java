package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        validation(target);
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validation(Path target) {
        if (!target.toString().endsWith(".zip")) {
            throw new IllegalArgumentException("Файл '" + target + "' не является файлом zip");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        String sourceName = jvm.get("d");
        var source =  Path.of(sourceName);
        var exclude = jvm.get("e");
        Search.validation(new String[] {sourceName, exclude});
        if (!sourceName.endsWith("\\")) {
            sourceName = sourceName + "\\";
        }
        var target = Path.of(sourceName + jvm.get("o"));
        var search = Search.search(source, p -> !p
                .toFile()
                .getName()
                .endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(search, target);
    }
}
