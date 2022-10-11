package com.example.a75;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inText = findViewById(R.id.L3InText);
        Spinner spinner = findViewById(R.id.L3Spinner);
        Button addBttn = findViewById(R.id.L3AddBttn);
        LinearLayout dataLayout = findViewById(R.id.L3DataLayout);

        addBttn.setOnClickListener(view -> {
            Button btn = new Button(getApplicationContext());
            //програмное создание экрана
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            btn.setLayoutParams(lp);
            btn.setText((CharSequence) inText.getText().toString());
            switch ((int) spinner.getSelectedItemId()) {
                case 0:
                    btn.setBackgroundColor(Color.WHITE);
                    break;
                case 1:
                    btn.setBackgroundColor(Color.BLUE);
                    break;
                case 2:
                    btn.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    btn.setBackgroundColor(Color.GREEN);
                    break;
                case 4:
                    btn.setBackgroundColor(Color.BLACK);
                    break;
            }
            dataLayout.addView(btn);
        });

    }
}
