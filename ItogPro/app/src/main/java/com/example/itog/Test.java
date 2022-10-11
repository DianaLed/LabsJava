package com.example.itog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    AllQuestion TestQ;
    MyQuestion question;
    TextView textViewQ;
    TextView inf;
    Button b1, b2, b3, b4;
    Button res;
    ImageView imageView;
    Integer nom_b;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        resources = getResources();
        inf=(TextView) findViewById(R.id.info);
        textViewQ = (TextView) findViewById(R.id.question);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        res = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.Picture);
        nom_b = 0;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom_b = 0;
                AllButGray();
                b1.setBackgroundColor(resources.getColor(R.color.red, null));
                trueButtonColor();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom_b = 1;
                AllButGray();
                b2.setBackgroundColor(resources.getColor(R.color.red, null));
                trueButtonColor();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom_b = 2;
                AllButGray();
                b3.setBackgroundColor(resources.getColor(R.color.red, null));
                trueButtonColor();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom_b = 3;
                AllButGray();
                b4.setBackgroundColor(resources.getColor(R.color.red, null));
                trueButtonColor();
            }
        });


        TestQ = new AllQuestion();
        //imageView.setImageResource();

        res.setText("Далее");
        res.setVisibility(View.GONE);
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestQ.end(nom_b==question.right);
                if(TestQ.next()) nextQ();
                else{
                    Intent intent =new Intent(Test.this, ResultAndSave.class);
                    intent.putExtra("size_of_true", TestQ.size_right);
                    startActivity(intent);
                }
            }
        });

        nextQ();
    }

    void trueButtonColor() {
        switch (question.right) {
            case 0:
                b1.setBackgroundColor(resources.getColor(R.color.green, null));
                break;
            case 1:
                b2.setBackgroundColor(resources.getColor(R.color.green, null));
                break;
            case 2:
                b3.setBackgroundColor(resources.getColor(R.color.green, null));
                break;
            case 3:
                b4.setBackgroundColor(resources.getColor(R.color.green, null));
                break;
            default:
                break;
        }

    }

    void AllButGray() {
        b1.setBackgroundColor(resources.getColor(R.color.gray, null));
        b2.setBackgroundColor(resources.getColor(R.color.gray, null));
        b3.setBackgroundColor(resources.getColor(R.color.gray, null));
        b4.setBackgroundColor(resources.getColor(R.color.gray, null));

        b1.setClickable(false);
        b2.setClickable(false);
        b3.setClickable(false);
        b4.setClickable(false);

        if(!TestQ.next())res.setText("Просмотреть результат");
        res.setVisibility(View.VISIBLE);

    }

    void AllButNormal() {
        res.setVisibility(View.GONE);
        b1.setBackgroundColor(resources.getColor(R.color.my, null));
        b2.setBackgroundColor(resources.getColor(R.color.my, null));
        b3.setBackgroundColor(resources.getColor(R.color.my, null));
        b4.setBackgroundColor(resources.getColor(R.color.my, null));

        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
    }

    void nextQ() {
        if (TestQ.next()) {
            question = TestQ.start();
            imageView.setImageResource(question.idPictures);
            textViewQ.setText(question.NameOfQ);
            b1.setText(question.variants.get(0));
            b2.setText(question.variants.get(1));
            b3.setText(question.variants.get(2));
            b4.setText(question.variants.get(3));
            AllButNormal();
            inf.setText(TestQ.infoStr());
        }

        //меняем вопрос, картинку
        //меняем цвета кнопок, текст, возвращаем кликабельность
        //исчезает кнопка дальше

    }
}