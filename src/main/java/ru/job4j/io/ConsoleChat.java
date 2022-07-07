package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void introduction() {
        System.out.println("Консольный чат.");
        System.out.println("Программа отвечает рандомными фразами");
        System.out.println("Чтобы приостановить ответы бота, напишите: " + STOP);
        System.out.println("Чтобы продолжить ответы бота, напишите: " + CONTINUE);
        System.out.println("Для завершения программы введите слово: " + OUT);
    }

    public static int getRandomNumber(int size) {
        double x = Math.random() * size;
        return (int) x;
    }

    public void run() {
        List<String> answers = readPhrases();
        if (answers.size() == 0) {
            throw new IllegalArgumentException("Файл ответов пуст");
        }
        List<String> log = new ArrayList<>();
        introduction();
        Scanner scanner = new Scanner(System.in);
        boolean state = true;
        String phrase;
        do {
            phrase = scanner.nextLine();
            log.add(phrase);
            if ((phrase.equals(OUT) || phrase.equals(STOP)) && state) {
                state = false;
            } else if (phrase.equals(CONTINUE) && !state) {
                state = true;
            }
            if (state) {
                var answer = answers.get(getRandomNumber(answers.size()));
                System.out.println(answer);
                log.add(answer);
            }
        } while (!phrase.equals(OUT));
        saveLog(log);
    }

    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("Windows-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\log.txt",
                "C:\\projects\\job4j_design\\answers.txt");
        cc.run();
    }
}
