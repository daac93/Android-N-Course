package com.daac.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar timeSeekBar;
    private TextView remainingTime;
    private Button startButton;
    private CountDownTimer countDownTimer;
    private MediaPlayer mediaPlayer;

    private int time = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = findViewById(R.id.timeSeekBar);
        //timeSeekBar.setMin(1);

        remainingTime = findViewById(R.id.remainingTime);
        startButton = findViewById(R.id.startButton);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);


        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int seconds = i % 60;
                int minutes = i / 60;

                remainingTime.setText(minutes + "." + seconds);
                time = i * 1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startTimer(View view)   {
        timeSeekBar.setEnabled(false);
        startButton.setText("STOP");

        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(time, 500) {
                @Override
                public void onTick(long l) {
                    time -= 500;
                    int seconds = (time/1000) % 60;
                    int minutes = (time/1000) / 60;

                    remainingTime.setText(minutes + "." + seconds);
                }

                @Override
                public void onFinish() {
                     //Play Audio
                    mediaPlayer.start();
                    restart();
                }
            }.start();
        }   else    {
            countDownTimer.cancel();
            mediaPlayer.stop();
            restart();
        }

    }

    public void restart()   {
        startButton.setText("START");
        countDownTimer = null;
        time = 1;
        timeSeekBar.setEnabled(true);
        timeSeekBar.setProgress(1);
        mediaPlayer.reset();
    }
}
