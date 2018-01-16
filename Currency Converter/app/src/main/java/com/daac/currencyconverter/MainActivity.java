package com.daac.currencyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertCurrency(View view)  {
        EditText amount = findViewById(R.id.currencyAmount);

        Double amountAsDouble = Double.parseDouble(amount.getText().toString());

        amountAsDouble = amountAsDouble * 570;

        Toast.makeText(this, amountAsDouble.toString(), Toast.LENGTH_LONG).show();
    }
}
