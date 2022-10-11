package com.example.a62lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listSt= new ArrayList<String>();
    String start="Элемент ";
    Integer nom=1;
    ListView listView;
    Button plusEl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusEl=(Button)  findViewById(R.id.button);
        listSt.add("Элемент 1");
        getSupportActionBar().hide();
        listView = findViewById(R.id.lv);
        PaintAd();

        plusEl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom++;
                listSt.add(start+nom.toString());
                PaintAd();
            }
        });
    }
    void ClickOnViev(int nom){
        listSt.remove(nom);
        AdaptButton adapter = new AdaptButton(this, R.layout.list_item, listSt, this);
        listView.setAdapter(adapter);
    }
    void PaintAd(){
        AdaptButton adapter = new AdaptButton(this, R.layout.list_item, listSt, this);
        listView.setAdapter(adapter);
    }
}