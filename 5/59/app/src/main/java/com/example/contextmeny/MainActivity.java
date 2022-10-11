package com.example.contextmeny;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>  arrayList=new ArrayList<String>();
    TextView tv;
    TextView click_tv;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click_tv=findViewById(R.id.textView);
        tv=findViewById(R.id.textView2);
        button=findViewById(R.id.button);
        arrayList.add("Кот");
        arrayList.add("Кошка");
        arrayList.add("Котенок");
        arrayList.add("Хорек");
        registerForContextMenu(click_tv);
        registerForContextMenu(button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        for(int i=0; i<arrayList.size(); i++){
            menu.add(Menu.NONE, i, Menu.NONE, arrayList.get(i));
        }
    }
    public boolean onContextItemSelected(MenuItem item)
    {
        tv.setText(arrayList.get(item.getItemId())+", Индекс: "+item.getItemId());
        return true;
    }
}