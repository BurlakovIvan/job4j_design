package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            var sourceSplit = in.lines()
                    .map(s -> s.split(" "))
                    .toList();
            var openInterval = false;
            for (String[] str : sourceSplit) {
                boolean status = "400".equals(str[0]) || "500".equals(str[0]);
                if (status && !openInterval) {
                    out.print(str[1] + ";");
                    openInterval = true;
                } else if (openInterval && !status) {
                    out.println(str[1] + ";");
                    openInterval = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analysis = new Analizy();
        analysis.unavailable("unavailable.csv", "target.csv");
    }
}
