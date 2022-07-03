package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read = 0;
            while (read != -1) {
                read = in.read();
                if ((char) read != '\r' && (char) read != '\n' && read != -1) {
                    text.append((char) read);
                } else if (!text.isEmpty()) {
                    var number = Integer.parseInt(text.toString());
                    text = new StringBuilder();
                    if (number % 2 == 0) {
                        System.out.println("" + number + " - четное число");
                    } else {
                        System.out.println("" + number + " - нечетное число");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

