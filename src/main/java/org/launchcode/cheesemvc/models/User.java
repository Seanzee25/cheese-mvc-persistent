package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class User {

    private int userId;
    private static int nextId;

    @NotNull
    @Size(min=5, max=15, message="Username must be between 5 and 15 characters")
    private String username;

    @NotNull
    @Size(min=1, message="Required")
    private String email;

    @NotNull
    @Size(min=1, message="Required")
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
