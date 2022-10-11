package com.example.a52;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button next;
    Button last;
    Button see;
    Integer st=1;
    TextView nomSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next=(Button)findViewById(R.id.button2);
        last=(Button)findViewById(R.id.button);
        see=(Button)findViewById(R.id.button3);
        nomSt=(TextView)findViewById(R.id.textView4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st++;
                nomSt.setText(st.toString());
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(st==1){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Как ты посмел удалить единственную странчку!", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    st--;
                    nomSt.setText(st.toString());
                }

            }
        });
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("size_st", st);
                startActivity(intent);
            }
        });

    }
}