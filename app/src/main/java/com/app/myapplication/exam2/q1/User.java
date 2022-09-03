package com.app.myapplication.exam2.q1;

public class User {
    private int id;
    private String full_name, email, password;

    public User(int id, String full_name, String email, String password) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public User(String full_name, String email, String password) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
