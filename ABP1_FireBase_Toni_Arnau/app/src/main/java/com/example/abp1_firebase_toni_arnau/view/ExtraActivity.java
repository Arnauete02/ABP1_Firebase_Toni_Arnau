package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.view.ViewActivity;

public class ExtraActivity extends AppCompatActivity implements ViewActivity {

    private EditText textPalabraAna;
    private TextView textAnaCrono;
    private TextView textAnaPalabra;
    private Button buttoAna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().extraActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        textPalabraAna = findViewById(R.id.textPalabraAna);
        textAnaCrono = findViewById(R.id.textAnaCrono);
        textAnaPalabra = findViewById(R.id.textAnaPalabra);
        buttoAna = findViewById(R.id.buttonAna);
    }

    public EditText getTextPalabraAna() {
        return textPalabraAna;
    }

    public void setTextPalabraAna(EditText textPalabraAna) {
        this.textPalabraAna = textPalabraAna;
    }

    public TextView getTextAnaCrono() {
        return textAnaCrono;
    }

    public void setTextAnaCrono(TextView textAnaCrono) {
        this.textAnaCrono = textAnaCrono;
    }

    public TextView getTextAnaPalabra() {
        return textAnaPalabra;
    }

    public void setTextAnaPalabra(TextView textAnaPalabra) {
        this.textAnaPalabra = textAnaPalabra;
    }

    public Button getButtoAna() {
        return buttoAna;
    }

    public void setButtoAna(Button buttoAna) {
        this.buttoAna = buttoAna;
    }
}