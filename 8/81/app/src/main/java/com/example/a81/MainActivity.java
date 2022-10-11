package com.example.a81;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.Chislo);
        Button incBttn = findViewById(R.id.Plus);
        Button resetBttn = findViewById(R.id.Sb);

        Counter c = new Counter();

        incBttn.setOnClickListener(view -> {
            c.increase();
            textView.setText((CharSequence) Long.toString(c.getCount()));
        });

        resetBttn.setOnClickListener(view -> {
            c.reset();
            textView.setText((CharSequence) Long.toString(c.getCount()));
        });


    }
}