package com.example.a66lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button save, next_act;
    DBHelper dbHelper;
    EditText inpTe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);



        save=findViewById(R.id.save);
        next_act=findViewById(R.id.Go);
        inpTe=findViewById(R.id.InpText);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inpTe.getText().toString();
                SQLiteDatabase database = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_NOD, name);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                dbHelper.close();
                inpTe.setText("");
            }
        });

        next_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        dbHelper.close();
    }
}