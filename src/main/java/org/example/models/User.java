package org.example.models;

import org.example.Data;

public class User {
    private static int ID = 0;
    private String username;
    private String password;
    private int userID;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        ID++;
        userID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sign(String username, String password) {
        setPassword(password);
        setUsername(username);
    }

    public int getUserID() {
        return userID;
    }
}
