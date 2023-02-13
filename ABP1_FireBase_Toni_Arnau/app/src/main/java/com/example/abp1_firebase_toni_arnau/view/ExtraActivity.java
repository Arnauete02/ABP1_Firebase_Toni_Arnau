package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class ExtraActivity extends AppCompatActivity implements ViewActivity {

    private EditText textPalabraAnaInput;
    private TextView textAnaCrono;
    private TextView textAnaPalabraMostrar;
    private Button buttoAna;
    private TextView textPalabraAnaDos;

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

    public TextView getTextPalabraAnaDos() {
        return textPalabraAnaDos;
    }

    public void setTextPalabraAnaDos(TextView textPalabraAnaDos) {
        this.textPalabraAnaDos = textPalabraAnaDos;
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        textPalabraAnaInput = findViewById(R.id.textPalabraAnaInput);
        textAnaCrono = findViewById(R.id.textAnaCrono);
        textAnaPalabraMostrar = findViewById(R.id.textPalabraAnaMostrar);
        buttoAna = findViewById(R.id.buttonAna);
        textPalabraAnaDos = findViewById(R.id.textPalabraAnaDos);
    }

    public EditText getTextPalabraAnaInput() {
        return textPalabraAnaInput;
    }

    public TextView getTextAnaCrono() {
        return textAnaCrono;
    }

    public TextView getTextAnaPalabraMostrar() {
        return textAnaPalabraMostrar;
    }

    public Button getButtoAna() {
        return buttoAna;
    }
}