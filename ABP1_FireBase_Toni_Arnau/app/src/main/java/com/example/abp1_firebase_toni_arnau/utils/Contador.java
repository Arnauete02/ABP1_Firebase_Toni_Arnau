package com.example.abp1_firebase_toni_arnau.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abp1_firebase_toni_arnau.controller.Controller;
import com.example.abp1_firebase_toni_arnau.view.ExtraActivity;

public class Contador extends CountDownTimer {
    private final String TAG = Contador.class.getSimpleName();
    private Boolean isCountDown = false;
    private long lastCountDown = 100000;
    private ExtraActivity activity;

    public Contador(long millisInFuture, long countDownInterval, ExtraActivity activity) {
        super(millisInFuture, countDownInterval);
        isCountDown = true;
        this.activity = activity;
    }

    @Override
    public void onFinish() {
        this.activity.getTextPalabraAnaDos().setVisibility(View.VISIBLE);
        this.activity.getTextAnaCrono().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Controller.getInstance().returnCollectedDataTimer(String.valueOf(millisUntilFinished/1000));
    }
}
