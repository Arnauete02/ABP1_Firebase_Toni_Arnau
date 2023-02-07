package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.utils.Providers;

public class User {
    private String name;
    private String email;
    private String password;
    private Providers provider;

    private static User user;

    public static User getInstance() {
        if (user == null) user = new User();
        return user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Providers getProvider() {
        return provider;
    }

    public static User getUser() {
        return user;
    }
}
