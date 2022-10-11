package com.example.a76;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Date d = new Date();
        EditText text = findViewById(R.id.tt);
        String t = Integer.toString(d.hour) + "/" +
                Integer.toString(d.min) + "/" + Integer.toString(d.sec);
        text.setText(t);
        Handler.Callback hc = new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                d.addSecond();
                String time = Integer.toString(d.hour) + "/" +
                        Integer.toString(d.min) + "/" + Integer.toString(d.sec);
                text.setText(time);
                h.sendEmptyMessageDelayed(0, 1000);
                return true;
            }
        };
        h = new Handler(hc);
        Button timer = findViewById(R.id.L4Bttn);
        timer.setOnClickListener(view -> {
            if (h.hasMessages(0))
                h.removeMessages(0);
            else
                h.sendEmptyMessageDelayed(0, 1000);
        });
    }

    public static class Date {
        public int hour;
        public int min;
        public int sec;
        public Date() {
            hour = min = sec = 0;
        }

        public void addSecond() {
            sec += 1;
            sec = sec % 60;
            min = min + sec / 60;
            min = min % 60;
            hour = hour + min / 60;
            hour = hour % 24;
        }

        public String toSt() {
            return "Hour: " + hour + "| Min: " + min + "| Sec: " + sec;
        }
    }

}
