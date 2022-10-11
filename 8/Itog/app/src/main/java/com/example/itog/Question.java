package com.example.itog;

import java.util.ArrayList;

public class Question {
    public ArrayList<String> Answers;
    public String TextQuestion;
    public String NameFoto;
    int nomTrue;
    int nomV;

    public Question( String textQuestion, String a1, String a2, String a3,String a4, String nameFoto, int nomTrue){
        Answers=new ArrayList<String>();
        Answers.add(a1);
        Answers.add(a2);
        Answers.add(a3);
        Answers.add(a4);
        NameFoto=nameFoto;
    }
}
