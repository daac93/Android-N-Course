package com.daac.phrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playPhraseAudio(View view)  {
        String audioFile = (String) view.getTag();
        int resourceId = -1;

        resourceId = getResources().getIdentifier(audioFile, "raw", "com.daac.phrases");

        if(resourceId != -1)    {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resourceId);
            mediaPlayer.start();
        }   else    {
            Log.e("Error", "File not found: " + audioFile);
        }
    }
}
