package org.example.accounts;

import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private UserRepository repository;

    public AccountService() {
        this.repository = new UserRepository();
    }
    public void AddNewUser(UserProfile user) {
        repository.CreateUser(user);
    }

    public UserProfile getUserByLogin(String login) {
        return repository.GetUser(login);
    }
}
