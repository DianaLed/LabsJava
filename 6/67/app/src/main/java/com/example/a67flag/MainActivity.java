package com.example.a67flag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    private CheckBox flag;
    Boolean a=false;

    public static final String APP_PREFERENCES = "mysettings";
    final String KEY_FlAG_INDEX = "SAVED_FLAG";

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
    }

    protected void onstart() { //перед созданием активити
        SharedPreferences sharedPreferences = getSharedPreferences(
                APP_PREFERENCES, MODE_PRIVATE);
        Boolean savedRadioIndex = sharedPreferences.getBoolean(
                KEY_FlAG_INDEX, a);
        flag.setChecked(savedRadioIndex);
    }

    protected void save() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_FlAG_INDEX, a);
        editor.apply();
    }

}