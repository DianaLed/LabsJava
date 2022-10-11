package com.example.a66lab;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    //String[] notes;
    ArrayList<String> notes = new ArrayList<String>();
    ArrayList<Integer> idn = new ArrayList<Integer>();
    DBHelper dbHelper;
    ListView lvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper = new DBHelper(this);
        // находим список
        lvMain = (ListView) findViewById(R.id.lv_notes);

        InpNoteFromDB();
        // создаем адаптер
        Adapt();
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        notes.get(position), Toast.LENGTH_SHORT);
                toast.show();
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + idn.get(position), null);
                InpNoteFromDB();
                Adapt();
            }
        });

        dbHelper.close();
    }

    private void Adapt(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, notes);
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
    }

    private void InpNoteFromDB(){
        notes.clear();
        idn.clear();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int nodeIndex = cursor.getColumnIndex(DBHelper.KEY_NOD);
            int nodeId = cursor.getColumnIndex(DBHelper.KEY_ID);
            do {
                notes.add(cursor.getString(nodeIndex));
                idn.add(cursor.getInt(nodeId));
            } while (cursor.moveToNext());
        }// else
            //Log.d("mLog","0 rows");
        cursor.close();
    }

}