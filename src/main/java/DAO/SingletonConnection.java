package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    private static Connection connect = null;
    private static String lastError = null;

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/locationenligne?serverTimezone=UTC";

    private SingletonConnection() {
    }

    private static String config(String envKey, String fallback) {
        String value = System.getenv(envKey);
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return value;
    }

    private static String configRequired(String envKey) {
        String value = System.getenv(envKey);
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value;
    }

    private static Connection openConnection() {
        String url = config("DB_URL", DEFAULT_URL);
        String user = configRequired("DB_USER");
        String password = configRequired("DB_PASSWORD");

        if (user == null || password == null) {
            lastError = "DB_USER or DB_PASSWORD is missing in environment variables.";
            System.err.println(lastError);
            return null;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lastError = null;
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized Connection getInstance() {
        try {
            if (connect == null || connect.isClosed()) {
                connect = openConnection();
            }
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            connect = openConnection();
        }
        return connect;
    }

    public static synchronized String getLastError() {
        return lastError;
    }

}
