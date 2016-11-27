package ru.skanerxxl.rambler.database.entity;

public class Administrator {
    private String login = "admin";
    private String password = "admin";
    private String roles = "ADMIN";

    public Administrator() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
