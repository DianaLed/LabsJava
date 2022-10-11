package com.example.sql_study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sql_study.db.MyDbManager;

public class MainActivity extends AppCompatActivity {
private MyDbManager myDbManager;
    private EditText edTitle, edDis;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbManager=new MyDbManager(this);
        //edTitle=findViewById(R.id.edTitle);
        //edDis=findViewById(R.id.edDisc);
        t=findViewById(R.id.textView);
    }

    @Override
    protected void onResume() { //когда создастся активити
        super.onResume();
        myDbManager.openDb();

        //прописываем
//        for(String title:myDbManager.getFromDb()){
//            t.append(title);
//            t.append("\n");
//        }
    }
//
//    public void OnClickSave(View view){
////        myDbManager.insertToDb(edTitle.getText().toString(), edDis.getText().toString());
////
////        for(String title:myDbManager.getFromDb()){
////            t.append(title);
////            t.append("\n");
////        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //закрываем бд
//        myDbManager.closeDb();
//    }
}