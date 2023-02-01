package com.example.abp1_firebase_toni_arnau.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class LoginActivity extends AppCompatActivity implements ViewActivity {

    private Button registerButton;
    private Button loginButton;
    private Button googleButton;
    private EditText mail;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_ABP1_FireBase_Toni_Arnau);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().loginActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        registerButton = findViewById(R.id.button_register);
        loginButton = findViewById(R.id.button_login);
        googleButton = findViewById(R.id.button_google);
        mail = findViewById(R.id.editText_mail);
        password = findViewById(R.id.editText_Password);
    }

    public Button getRegisterButton(){
        return registerButton;
    }

    public Button getLoginButton(){
        return loginButton;
    }

    public Button getGoogleButton(){
        return googleButton;
    }

    public EditText getMail() {
        return mail;
    }

    public EditText getPassword() {
        return password;
    }
}