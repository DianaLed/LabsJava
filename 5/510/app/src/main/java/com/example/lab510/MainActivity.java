package com.example.lab510;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        TextView infoTextView = findViewById(R.id.tv);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_cat1:
                infoTextView.setText("Вы выбрали кота! Индекс 1");
                return true;
            case R.id.action_cat2:
                infoTextView.setText("Вы выбрали кошку!Индекс 2");
                return true;
            case R.id.action_cat3:
                infoTextView.setText("Вы выбрали котёнка!Индекс 3");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}