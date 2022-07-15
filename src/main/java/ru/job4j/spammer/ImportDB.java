package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .filter(s -> s.length() > 0)
                    .forEach(s -> {
                    var str = getLineData(s);
                    users.add(new User(str[0], str[1]));
            });
        }
        return users;
    }

    public String[] getLineData(String strings) {
        var str = strings.split(";");
        if (strings.length() < 9 || !strings.endsWith(";") || str.length != 2) {
            throw new IllegalArgumentException("Данные в строке '"
                    + strings + "' не соответсвуют шаблону ИмяПользователя;mail@email.com;");
        }
        if (isValidEmail(str[1])) {
            throw new IllegalArgumentException("Электронная почта в строке '"
                    + strings + "' не удовлетворяет шалону mail@email.ru");
        }
        return str;
    }

    public static boolean isValidEmail(String email) {
        String pattern = "^(\\w+([-.][A-Za-z0-9]+)*){2,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
        return !Pattern.matches(pattern, email);
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             cnt.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
