package com.daac.timestable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar);

        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, calculateTimesTable(1));

        ListView listView = findViewById(R.id.timesTableListView);
        listView.setAdapter(arrayAdapter);
        //seekBar.setMin(1); - Not compatible with current API level


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                arrayAdapter.clear();
                arrayAdapter.addAll(calculateTimesTable(i));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private ArrayList<String> calculateTimesTable(int baseNumber)  {
        ArrayList<String> timesTable = new ArrayList<>(10);

        for(int i = 1; i <= 10; i++)    {
            timesTable.add(Integer.toString(i * baseNumber));
        }

        return timesTable;
    }
}
