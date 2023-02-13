package com.example.abp1_firebase_toni_arnau.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class ParaulogicActivity extends AppCompatActivity implements ViewActivity {
    private EditText editTextPala;
    private TextView textViewAcier;
    private Button buttonPala;
    private ImageView imageViewPala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paraulogic);
        createAllItemsAsGlobalWithGetters();
        callControllerWithThisActivityAsParameter();
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().paraulogicActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {
        textViewAcier = findViewById(R.id.textPalaNumAcier);
        imageViewPala = findViewById(R.id.imagePala);
        editTextPala = findViewById(R.id.textLetraPala);
        buttonPala = findViewById(R.id.buttonPala);

    }

    public EditText getEditTextPala() {
        return editTextPala;
    }

    public TextView getTextViewAcier() {
        return textViewAcier;
    }

    public Button getButtonPala() {
        return buttonPala;
    }

    public ImageView getImageViewPala() {
        return imageViewPala;
    }

    public void setEditTextPala(EditText editTextPala) {
        this.editTextPala = editTextPala;
    }

    public void setTextViewAcier(TextView textViewAcier) {
        this.textViewAcier = textViewAcier;
    }

    public void setButtonPala(Button buttonPala) {
        this.buttonPala = buttonPala;
    }

    public void setImageViewPala(ImageView imageViewPala) {
        this.imageViewPala = imageViewPala;
    }
}