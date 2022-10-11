package com.example.a56lab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Lab5";
    public static final String TABLE_CONTACTS = "PlanToDay";

    public static final String KEY_ID = "_id";
    public static final String KEY_DAY = "day";
    public static final String KEY_MON = "mon";
    public static final String KEY_YEAR = "ye";
    public static final String KEY_DES = "DES";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "("
                + KEY_ID + " integer primary key,"
                + KEY_YEAR + " integer,"
                + KEY_MON + " integer,"
                + KEY_DAY + " integer,"
                + KEY_DES + " text"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);
    }
}
