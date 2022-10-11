package com.example.a85;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyStringClass myStringClass;
    TextView info;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button plus, del;
        info=findViewById(R.id.textView);
        input=findViewById(R.id.editTextTextPersonName);
        plus=findViewById(R.id.Plus);
        del=findViewById(R.id.Del);

        myStringClass=new MyStringClass();
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=input.getText().toString();
                if(a.isEmpty()){
                    Toast.makeText(MainActivity.this, "Будь человеком, введи хоть что-то...", Toast.LENGTH_SHORT).show();
                }
                else myStringClass.add(a);
                new_str();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myStringClass.dellLast();
                new_str();
            }
        });

    }
    public void new_str(){
        info.setText(myStringClass.getAllElemInOneStr());
    }
}