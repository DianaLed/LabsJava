package com.example.a71;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements SvetView.StateChangedCallback {
    private Patinson pat;
    private SvetView svetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout DataLayout = findViewById(R.id.LL);
        svetView = new SvetView(getApplicationContext(), 100, 400, this);
        pat = new Patinson(getApplicationContext(), -1, 150, 0);
        DataLayout.addView(svetView);
        DataLayout.addView(pat);
    }

    @Override
    public void stateGreen() {
        pat.startAnim();
    }
}