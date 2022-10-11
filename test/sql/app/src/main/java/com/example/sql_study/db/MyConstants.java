package com.example.sql_study.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table"; //название таблицы
    public static final String _ID = "_id";//название первой колонны
    public static final String TITLE = "title"; //название второй колонны
    public static final String DISC = "disc";
    public static final String DB_NAME = "my_db.db"; //название БД
    public static final int DB_VERSION = 1; //версия БД

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS" + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            TITLE + " TEXT," +
            DISC + " TEXT)";
    //струкура таблицы

    public static final String DROP =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    //что бы удалить
}
