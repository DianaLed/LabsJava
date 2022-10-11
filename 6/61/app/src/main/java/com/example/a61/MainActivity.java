package com.example.a61;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listSt= new ArrayList<String>();
    ArrayList<Integer> listCol= new ArrayList<Integer>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        listView = findViewById(R.id.lv);
        work_but();
        AdaptButton adapter = new AdaptButton(this, R.layout.list_item, listCol, listSt);
        listView.setAdapter(adapter);
    }



    private void work_but(){
        listSt.add("Белый");
        listSt.add("Черный");
        listSt.add("Синий");
        listCol.add(Color.WHITE);
        listCol.add(Color.BLACK);
        listCol.add(Color.BLUE);
    }
}