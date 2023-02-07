package com.example.abp1_firebase_toni_arnau.model;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.utils.Providers;

public class User {
    private String name;
    private String email;
    private Providers provider;
    private String username;

    public User() {

    }

    public User(String name, String email, Providers provider, String username) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Providers getProvider() {
        return provider;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
