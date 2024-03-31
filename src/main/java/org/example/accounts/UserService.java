package org.example.accounts;

import org.example.repository.UserRepository;

public class UserService {

    private UserRepository repository;

    public UserService() {
        this.repository = new org.example.repository.UserRepository();
    }

    public void AddNewUser(UserProfile user) {
        repository.CreateUser(user);
    }

    public UserProfile getUserByLogin(String login) {
        return repository.GetUser(login);
    }
}
