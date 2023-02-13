package com.example.abp1_firebase_toni_arnau.utils;

import android.os.CountDownTimer;
import android.util.Log;

public class Contador extends CountDownTimer {
    private final String TAG = Contador.class.getSimpleName();
    private Boolean isCountDown = false;
    private long lastCountDown = 100000;

    public Contador(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        isCountDown = true;
    }

    @Override
    public void onFinish() {
        Log.i(TAG, "onFinish: ");
        isCountDown = false;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.d(TAG, "onTick: " + String.valueOf(millisUntilFinished/1000));
        lastCountDown = millisUntilFinished;

    }
}
