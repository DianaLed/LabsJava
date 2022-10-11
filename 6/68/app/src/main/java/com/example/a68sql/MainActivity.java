package com.example.a68sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox flag;
    Boolean a=false;
    DBHelper dbHelper;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        flag=(CheckBox)findViewById(R.id.checkBox);
        onstart();
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=flag.isChecked();
                save();
            }
        });

        // проверяем, первый ли раз открывается программа
        SharedPreferences sp = getSharedPreferences("my_settings",
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // выводим нужную активность
            dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_S, a);
            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
            dbHelper.close();

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); // не забудьте подтвердить изменения
        }
        onstart();
    }

    protected void getID(){
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int nodeId = cursor.getColumnIndex(DBHelper.KEY_ID);
            do {
                id=cursor.getInt(nodeId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }

    protected void onstart() { //получаем id и pyfxtybt
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int nodeIndex = cursor.getColumnIndex(DBHelper.KEY_S);
            int nodeId = cursor.getColumnIndex(DBHelper.KEY_ID);
            do {
                Integer b=cursor.getInt(nodeIndex);
                if(b==1) a=true;
                else a=false;
                id=cursor.getInt(nodeId);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }

    protected void save() {
        //удаляем
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Toast toast = Toast.makeText(getApplicationContext(),
                0, Toast.LENGTH_SHORT);
        toast.show();
        SQLiteDatabase database1 = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id, null);

        //создаем новый
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_S, a);
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        dbHelper.close();
        //получаем id
        getID();
    }
}