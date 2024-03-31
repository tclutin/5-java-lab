package org.example.accounts;

import org.example.repository.UserRepository;

public class UserService {

    private IUserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    public void AddNewUser(UserProfile user) {
        repository.CreateUser(user);
    }

    public UserProfile getUserByLogin(String login) {
        return repository.GetUser(login);
    }
}
