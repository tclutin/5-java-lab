package org.example.repository;

import java.sql.*;

public class DbContext {

    private Connection connection;
    private final Object lock = new Object();

    private void createConnection() {
        synchronized (lock) {
            if (connection == null) {
                try {
                    Driver driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
                    DriverManager.registerDriver(driver);
                    String url = "jdbc:postgresql://localhost:5432/postgres";
                    String username = "postgres";
                    String password = "root";
                    connection = DriverManager.getConnection(url, username, password);
                } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void execUpdate(String query, Object... params) {
        createConnection();
        synchronized (lock) {
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler, Object... params) {
        createConnection();
        synchronized (lock) {
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                ResultSet result = statement.executeQuery();
                if (!result.next()) {
                    return null;
                }
                return handler.handle(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        }
}
