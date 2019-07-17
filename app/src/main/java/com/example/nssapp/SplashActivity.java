package com.example.nssapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashActivity extends Activity implements Runnable {

    Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        mThread = new Thread(this);

        mThread.start();
    }

    @Override
    public void run(){
        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            finish();
        }
    }
}