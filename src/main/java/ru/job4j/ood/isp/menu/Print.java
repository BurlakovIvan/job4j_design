package ru.job4j.ood.isp.menu;

public class Print implements MenuPrinter {

    private static final String INDENTS = "----";

    @Override
    public void print(Menu menu) {
        StringBuilder builder = new StringBuilder();
        for (Menu.MenuItemInfo menuItem : menu) {
            String[] split = menuItem.getNumber().split("\\.");
            String repeat = INDENTS.repeat(split.length - 1);
            builder
                    .append(repeat)
                    .append(menuItem.getName())
                    .append(" ")
                    .append(menuItem.getNumber())
                    .append(System.lineSeparator());
        }
        System.out.print(builder);
    }
}
