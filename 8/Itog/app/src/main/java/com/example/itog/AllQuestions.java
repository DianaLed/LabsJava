package com.example.itog;

import java.util.ArrayList;

public class AllQuestions {
    public ArrayList<Question> Q;
    void AllQuestions(){
        Q=new ArrayList<Question>();
        Question a1=new Question("Как называется этот инструмент?", "Пробойник", "Морник", "Борд", "Сликер", "путь", 1);
        Q.add(a1);
    }
}
