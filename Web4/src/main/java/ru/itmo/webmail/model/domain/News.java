package ru.itmo.webmail.model.domain;

import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;
import ru.itmo.webmail.model.service.UserService;

import java.io.Serializable;

public class News implements Serializable {

    private long userId;
    private String text;

    public News(long userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getLogin() {
        UserService userService = new UserService();
        return userService.findById(userId).getLogin();
    }
}
