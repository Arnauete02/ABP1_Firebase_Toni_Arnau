package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.abp1_firebase_toni_arnau.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button button_register;
    private Button button_login;
    private Button button_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hook();


    }

    private void hook(){
        email = findViewById(R.id.editText_mail);
        password = findViewById(R.id.editText_Password);
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);
        button_google = findViewById(R.id.button_google);
    }
}