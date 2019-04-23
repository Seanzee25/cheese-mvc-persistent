package org.launchcode.cheesemvc.models;

import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDate;
import java.util.Date;

public class User {

    private int userId;
    private static int nextId;
    private String username;
    private String email;
    private String password;
    private LocalDate joinDate;

    public User() {
        userId = nextId++;
        joinDate = LocalDate.now();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDate getJoinDate() {
        return joinDate;
    }
}
