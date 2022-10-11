package com.example.a52;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView nomSt;
    Integer size_st;
    Integer n_st;

    Button next;
    Button last;
    Button see;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        next=(Button)findViewById(R.id.button7);
        last=(Button)findViewById(R.id.button5);
        see=(Button)findViewById(R.id.button8);
        nomSt=(TextView)findViewById(R.id.textView10);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            size_st = arguments.getInt("size_st");
            nomSt.setText(size_st.toString());
            n_st=size_st;
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n_st<size_st) n_st++;
                else n_st=1;
                nomSt.setText(n_st.toString());
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n_st>1) n_st--;
                else n_st=size_st;
                nomSt.setText(n_st.toString());

            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent1=new Intent(MainActivity2.this, MainActivity.class);
                //intent.putExtra("size_st", size_st);
                startActivity(intent1);
            }
        });
    }
}