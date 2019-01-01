package ru.itmo.webmail.model.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String login;
    private String passwordSha1;
    private long id;

    public String getLogin() {
        return login;
    }
    public String getEmail() {
        return email;
    }
    public String getPasswordSha1() {
        return passwordSha1;
    }
    public long getId() {return id;}
    public void setLogin(String login) {
        this.login = login;
    }
    public void setEmail(String email) { this.email = email; }
    public void setId(long id) { this.id = id;}

    public void setPasswordSha1(String passwordSha1) {
        this.passwordSha1 = passwordSha1;
    }
}
