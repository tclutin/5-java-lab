package org.example.repository;

import org.example.DbContext;
import org.example.accounts.UserProfile;

import java.sql.*;

public class UserRepository {

    private DbContext db;

    public UserRepository() {
        this.db = new DbContext();
    }

    public void CreateUser(UserProfile user) {
        try {
            String query = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPass());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserProfile GetUser(String login) {
        try {
            String query = "SELECT * FROM users WHERE login = ?";
            try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
                statement.setString(1, login);
                ResultSet set = statement.executeQuery();
                UserProfile user = null;
                if (set.next()) {
                    String username = set.getString("login");
                    String pass = set.getString("password");
                    String email = set.getString("email");
                    user = new UserProfile(username, pass, email);
                }
                set.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
