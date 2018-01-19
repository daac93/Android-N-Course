package com.daac.phrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playPhraseAudio(View view)  {
        String audioFile = (String) view.getTag();
        int resourceId = -1;

        Class raw = R.raw.class;
        Field[] fields = raw.getFields();
        for(Field field : fields)   {
            try {
                if (field.getName().equals(audioFile)) {
                    resourceId = field.getInt(null);
                }
            }   catch (IllegalAccessException iae)  {
                Log.e("Error", iae.toString());
            }
        }

        if(resourceId != -1)    {
            MediaPlayer.create(this, resourceId);
        }   else    {
            Log.e("Error", "File not found: " + audioFile);
        }
    }
}
