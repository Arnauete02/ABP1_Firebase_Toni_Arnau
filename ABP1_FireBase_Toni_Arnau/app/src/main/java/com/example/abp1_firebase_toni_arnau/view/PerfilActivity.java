package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class PerfilActivity extends AppCompatActivity  implements ViewActivity{
    private EditText editText_nombre;
    private EditText editText_mail_perfil;
    private EditText editText_alias;
    private TextView textViewProvider;
    private Button buttonPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().perfilActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        editText_nombre = findViewById(R.id.editText_nombre);
        editText_mail_perfil = findViewById(R.id.editText_mail_perfil);
        editText_alias = findViewById(R.id.editText_alias);
        textViewProvider = findViewById(R.id.textView_provider);
        buttonPerfil = findViewById(R.id.button_perfil);
    }

    public EditText getEditText_nombre() {
        return editText_nombre;
    }

    public EditText getEditText_mail_perfil() {
        return editText_mail_perfil;
    }

    public EditText getEditText_alias() {
        return editText_alias;
    }

    public TextView getTextViewProvider() {
        return textViewProvider;
    }

    public Button getButtonPerfil() {
        return buttonPerfil;
    }

    public void setEditText_nombre(EditText editText_nombre) {
        this.editText_nombre = editText_nombre;
    }

    public void setEditText_mail_perfil(EditText editText_mail_perfil) {
        this.editText_mail_perfil = editText_mail_perfil;
    }

    public void setEditText_alias(EditText editText_alias) {
        this.editText_alias = editText_alias;
    }

    public void setTextViewProvider(TextView textViewProvider) {
        this.textViewProvider = textViewProvider;
    }
}