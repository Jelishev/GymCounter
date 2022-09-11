package com.example.tamagotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private ProgressBar progressBar;
    private long timeSpan = 20000;
    private long timeInMil = timeSpan;
    private boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownText = findViewById(R.id.countdownTimer);
        countdownButton = findViewById(R.id.countdownButton);
        progressBar = findViewById(R.id.progressBar);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
    }

    public void startStop() {
        if (running) {
            stopTimer();
        }
        else {
            if (timeInMil == 0) {
                timeInMil = timeSpan;
            }
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeInMil, 1) {
            @Override
            public void onTick(long l) {
                timeInMil = l;
                updateTimer();
                updateProgressBar();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        running = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        running = false;
    }

    public void updateProgressBar() {
        if (timeInMil < 50) {
            timeInMil = 0;
        }
        int percent = (int) timeInMil * 10000 / (int) timeSpan;
        progressBar.setProgress((int)percent);
    }

    public void updateTimer() {
        int hours = (int) timeInMil / 3600000;
        int minutes = (int) timeInMil/60000;
        int seconds = (int) timeInMil % 60000 / 1000;
        //int mili = (int) timeInMil % 100;
        String timeLeftText = "";

        if (hours < 10 ) {timeLeftText += "0";};
        timeLeftText += hours;
        timeLeftText += " : ";
        if (minutes < 10 ) {timeLeftText += "0";};
        timeLeftText += minutes;
        timeLeftText += " : ";

        if (seconds < 10 ) {timeLeftText += "0";};
        timeLeftText += seconds;
        //timeLeftText += " : ";

        /*if (mili < 10 ) {timeLeftText += "0";};
        timeLeftText += mili;
        */
        countdownText.setText(timeLeftText);
        if (timeInMil < 50) {
            stopTimer();
            countdownText.setText("00 : 00 : 00");
        }
    }
}