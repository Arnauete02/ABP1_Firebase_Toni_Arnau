package com.example.abp1_firebase_toni_arnau.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.utils.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements ViewActivity {

    private Button registerButton;
    private Button loginButton;
    private Button googleButton;
    private EditText mail;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createAllItemsAsGlobalWithGetters();
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