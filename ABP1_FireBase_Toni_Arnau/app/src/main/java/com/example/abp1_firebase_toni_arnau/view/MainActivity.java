package com.example.abp1_firebase_toni_arnau.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abp1_firebase_toni_arnau.R;
import com.example.abp1_firebase_toni_arnau.controller.Controller;

public class MainActivity extends AppCompatActivity implements ViewActivity {
    private ImageView imageView;
    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_ABP1_FireBase_Toni_Arnau);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callControllerWithThisActivityAsParameter();

        //HOOK
        imageView = findViewById(R.id.img_rub);
        textView = findViewById(R.id.txt_nom);
        textView1 = findViewById(R.id.text_slogan);

        //SPLASH
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            MainActivity.this,
                            Pair.create(imageView, imageView.getTransitionName()),
                            Pair.create(textView, textView.getTransitionName()),
                            Pair.create(textView1, textView1.getTransitionName())
                    );
                    startActivity(intent,options.toBundle());
                }
            }
        },2000);
    }

    @Override
    public void callControllerWithThisActivityAsParameter() {
        Controller.getInstance().mainActivity(this);
    }

    @Override
    public void createAllItemsAsGlobalWithGetters() {

    }
}