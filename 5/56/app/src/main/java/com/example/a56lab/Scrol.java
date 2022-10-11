package com.example.a56lab;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Scrol extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();

    Button but_n;
    Button but_l;

    TextView d, i;
    Integer nom=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrol);
        InpInfFromDB();
        but_n = (Button) findViewById(R.id.Bnext);
        but_l = (Button) findViewById(R.id.Blast);
        d=findViewById(R.id.DateTV);
        i=findViewById(R.id.InfoTV);
        d.setText(date.get(nom));
        i.setText(info.get(nom));
        but_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom<info.size()-1){
                    nom++;
                    d.setText(date.get(nom));
                    i.setText(info.get(nom));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Вы находитесь на последней страничке, листать в эту сторону нельзя нельзя.", Toast.LENGTH_LONG).show();
                }
            }
        });
        but_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nom>0){
                    nom--;
                    d.setText(date.get(nom));
                    i.setText(info.get(nom));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Вы находитесь на первой страничке, листать в эту сторону нельзя.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void InpInfFromDB() {

        info.clear();
        date.clear();
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        ArrayList<Integer> sum = new ArrayList<Integer>();//показывает положение.
        if (cursor.moveToFirst()) {
            int nodeInf = cursor.getColumnIndex(DBHelper.KEY_DES);
            int nodeDay = cursor.getColumnIndex(DBHelper.KEY_DAY);
            int nodeMon = cursor.getColumnIndex(DBHelper.KEY_MON);
            int nodeY = cursor.getColumnIndex(DBHelper.KEY_YEAR);
            int nodeId = cursor.getColumnIndex(DBHelper.KEY_ID);
            do {
                info.add(cursor.getString(nodeInf));
                int a = cursor.getInt(nodeY);
                int b = cursor.getInt(nodeMon);
                int c = cursor.getInt(nodeDay);
                date.add( String.valueOf(c) + "." + String.valueOf(b + 1) + "." +String.valueOf(a));
                sum.add((a * 365) + (b * 30) + c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
        //сортировка
        for (int i = 0; i < sum.size()-1; i++) {
            int index = sum.indexOf(Collections.max(sum));
            sum.set(index, 0);
            Collections.swap(sum, i, index);
            Collections.swap(info, i, index);
            Collections.swap(date, i, index);
        }
    }
}