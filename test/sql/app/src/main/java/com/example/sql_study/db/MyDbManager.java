package com.example.sql_study.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context){
        this.context=context;
        myDbHelper=new MyDbHelper(context);
    }
    public void openDb(){
        db=myDbHelper.getWritableDatabase();
    }

    public void insertToDb(String title, String disc){
        ContentValues cv=new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DISC, disc);

        db.insert(MyConstants.TABLE_NAME, null, cv);

    }

    public List<String> getFromDb(){
        List<String> tempList=new ArrayList<>();
        Cursor cursor=db.query(MyConstants.TABLE_NAME, null, null,null,null,null,null);
        while(cursor.moveToNext()){
            Integer nom=cursor.getColumnIndex(MyConstants.TITLE);
            if(nom>=0){
                String title = cursor.getString(nom);
                tempList.add(title);
            }

        }
        cursor.close();
        return tempList;
    }

    public void closeDb(){
        myDbHelper.close();
    }
}
