package com.example.itog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultAndSave extends AppCompatActivity {
    Integer res;
    SharedPreferences sPref;
    SharedPreferences.Editor ed;
    Integer lres;

    String NameOfWinner;
    Integer ResOfWinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_and_save);

        Intent intent = getIntent();
        res = intent.getIntExtra("size_of_true", 0);
        //получили результат


        TextView NW=(TextView) findViewById(R.id.name);
        TextView RW=(TextView) findViewById(R.id.BestRes);
        TextView RY=(TextView) findViewById(R.id.YouRes);
        TextView textViewY=(TextView) findViewById(R.id.textView11);
        Button save=(Button) findViewById(R.id.saveRes);
        Button men=(Button) findViewById(R.id.saveRes2);
        EditText et=findViewById(R.id.editTextTextPersonName);

        //загрузили результат
        sPref = getPreferences(MODE_PRIVATE);
        ed = sPref.edit();
        load();

        if(res==-1){
            textViewY.setText("Последний результат");
            et.setVisibility(View.GONE);
            save.setVisibility(View.GONE);
            RY.setText(text_out(lres));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(res>ResOfWinner){
                    String name=et.getText().toString();
                    if(name=="") mess();
                    else {
                        saveBestRes(name);
                    }
                } //с диалогом
                saveLastRes();
            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ResultAndSave.this, MainActivity.class);
                startActivity(intent);
            }
        });





        if(res<ResOfWinner) et.setVisibility(View.GONE);
        //прописали
        NW.setText(NameOfWinner);
        RW.setText(text_out(ResOfWinner));
        if(res!=-1) RY.setText(text_out(res));

    }
    void mess(){
        Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
    }
    String text_out(Integer a){
        String res=new String();
        res=String.valueOf(a)+" из 10";
        return res;
    }

    void saveBestRes(String name){
        ed.putInt("BestResultI", res);
        ed.putString("BestResultN", name);
        ed.commit();
        Toast.makeText(this, "Результат сохранен.", Toast.LENGTH_SHORT).show();
    }
    void saveLastRes(){
        ed.putInt("ResultI", res);
        ed.commit();
        Toast.makeText(this, "Результат сохранен.", Toast.LENGTH_SHORT).show();
    }
    void load() {
        sPref = getPreferences(MODE_PRIVATE);
        NameOfWinner  = sPref.getString("BestResultN", "");
        ResOfWinner= sPref.getInt("BestResultI", 0);
        lres=sPref.getInt("ResultI", 0);
    }
}