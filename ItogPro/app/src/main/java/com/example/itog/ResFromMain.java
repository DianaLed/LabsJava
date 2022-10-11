package com.example.itog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResFromMain extends AppCompatActivity {
    SharedPreferences sPref;
    String NameOfWinner;
    Integer ResOfWinner;
    Integer ResOfLast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_from_main);
        Button last;
        sPref = getPreferences(MODE_PRIVATE);
        load();
        TextView NW=(TextView) findViewById(R.id.name1);
        TextView RW=(TextView) findViewById(R.id.besRes1);
        TextView RY=(TextView) findViewById(R.id.lastres1);

        NW.setText(NameOfWinner);
        RW.setText(text_out(ResOfWinner));
        RY.setText(text_out(ResOfLast));

        last=(Button)findViewById(R.id.back);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ResFromMain.this, MainActivity.class);
                //intent.putExtra("size_st", size_st);
                startActivity(intent);
            }
        });
    }
    String text_out(Integer a){
        String res=new String();
        res=String.valueOf(a)+" из 10";
        return res;
    }
    void load() {
        NameOfWinner  = sPref.getString("BestResultN", "D");
        ResOfWinner= sPref.getInt("BestResultI", 0);
        ResOfLast=sPref.getInt("ResultI", 0);
    }
}