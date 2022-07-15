package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName, Statement statement) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
        statement.execute(sql);
    }

    public void dropTable(String tableName, Statement statement) throws SQLException {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName,
                          String type, Statement statement) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD %s %s",
                tableName, columnName, type
        );
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName,
                           Statement statement) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName
        );
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName, Statement statement) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName
        );
        statement.execute(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            try (Statement statement = tableEditor.connection.createStatement()) {
                String tableName = "demo_table";
                tableEditor.createTable(tableName, statement);
                System.out.println(getTableScheme(tableEditor.connection, tableName));
                tableEditor.addColumn(tableName, "comment", "VARCHAR(250)", statement);
                System.out.println(getTableScheme(tableEditor.connection, tableName));
                tableEditor.renameColumn(tableName, "comment", "newComment", statement);
                System.out.println(getTableScheme(tableEditor.connection, tableName));
                tableEditor.dropColumn(tableName, "newComment", statement);
                System.out.println(getTableScheme(tableEditor.connection, tableName));
                tableEditor.dropTable(tableName, statement);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
