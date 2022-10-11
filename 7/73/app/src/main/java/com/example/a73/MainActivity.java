package com.example.a73;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout DataLayout = findViewById(R.id.LL);
        T3View t3View = new T3View(getApplicationContext());
        DataLayout.addView(t3View);

    }
}