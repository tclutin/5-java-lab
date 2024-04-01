package org.example.repository;

import org.example.accounts.UserProfile;

import java.sql.*;

public class UserRepository{

    private DbContext db;

    public UserRepository() {
        this.db = new DbContext();
    }

    public void createUser(UserProfile user) {
        String query = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
        db.execUpdate(query, user.getLogin(), user.getPass(), user.getEmail());
    }

    public UserProfile getUser(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        UserProfile user = db.execQuery(query, set -> new UserProfile(
                set.getString("login"),
                set.getString("password"),
                set.getString("email")),
                login);
        return user;
    }
}
