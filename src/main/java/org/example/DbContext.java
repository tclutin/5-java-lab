package org.example;

import org.postgresql.util.LruCache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {

    private Connection connection;
    public void createConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String username = "postgres";
                String password = "root";
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        createConnection();
        return connection;
    }
}
