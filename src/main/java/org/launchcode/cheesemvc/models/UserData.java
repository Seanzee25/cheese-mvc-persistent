package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getAll() {
        return users;
    }

    public static void add(User user) {
        users.add(user);
    }

    public static void remove(int userId) {
        User user = getById(userId);
        users.remove(user);
    }

    public static User getById(int userId) {
        for(User user : users) {
            if(user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }
}
