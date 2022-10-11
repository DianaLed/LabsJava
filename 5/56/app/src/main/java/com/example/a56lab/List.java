package com.example.a56lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class List extends AppCompatActivity {
    ArrayList<String> info = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> id = new ArrayList<Integer>();
    DBHelper dbHelper;
    Button next;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        next=(Button) findViewById(R.id.button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(List.this, Scrol.class);
                startActivity(intent4);
            }
        });

        listView = findViewById(R.id.lv);
        //получить информацию из бд
        InpInfFromDB();
        Adapt();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id.get(position), null);
                InpInfFromDB();
                Adapt();
                return true;
            }
        });
    }

    private void Adapt(){
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, info) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(info.get(position));
                text2.setText(date.get(position));
                return view;
            }
        };
        listView.setAdapter(adapter);
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
                id.add(cursor.getInt(nodeId));
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
            Collections.swap(id, i, index);
        }
    }
}
