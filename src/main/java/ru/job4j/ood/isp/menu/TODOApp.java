package ru.job4j.ood.isp.menu;

import java.sql.SQLOutput;
import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final int NEW_TASK = 1;
    private static final int SHOW = 2;
    private static final String TITLE = """
            Введи
            1 - чтобы добавить пункт меню
            2 - чтобы вывести меню
            любую клавишу - чтобы выйти
            """;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        Print print = new Print();
        int menuChoice;
        do {
            System.out.println(TITLE);
            menuChoice = Integer.parseInt(sc.nextLine());
            if (menuChoice == SHOW) {
                print.print(menu);
            } else if (menuChoice == NEW_TASK) {
                System.out.println("Введи название основной задачи:");
                String parentName = sc.nextLine();
                menu.add(Menu.ROOT, parentName, STUB_ACTION);
                System.out.println("Введи название задачи");
                String childName = sc.nextLine();
                menu.add(parentName, childName, STUB_ACTION);
            }
        } while (menuChoice == SHOW || menuChoice == NEW_TASK);
        System.out.println("Завершение программы");
    }
}
