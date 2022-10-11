package com.example.a56lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlusSob extends AppCompatActivity {

    private Button button_save;
    EditText inp_inf;
    int mYear;
    int mMonth;
    int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_sob);
        button_save=findViewById(R.id.save);
        inp_inf=findViewById(R.id.inp_disc);

        //работа с календарем
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year,
                                            int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;

                String selectedDate = new StringBuilder().append(mMonth + 1)
                        .append("-").append(mDay).append("-").append(mYear)
                        .append(" ").toString();
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });

        DBHelper dbHelper= new DBHelper(this);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase database = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_DES, inp_inf.getText().toString());
                contentValues.put(DBHelper.KEY_DAY, mDay);
                contentValues.put(DBHelper.KEY_MON, mMonth);
                contentValues.put(DBHelper.KEY_YEAR, mYear);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                dbHelper.close();
                Intent intent2=new Intent(PlusSob.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}