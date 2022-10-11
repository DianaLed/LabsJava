package com.example.itog;

import java.util.ArrayList;
import java.util.Collections;

public class AllQuestion {
    public ArrayList<MyQuestion> questions;
    int size_was;
    int size_right;
    int index;
    AllQuestion(){
        questions=new ArrayList<MyQuestion>();
        size_was=0;
        size_right=0;
        index=0;
        PusQ("Как называется этот инструмент?", "Пробойник", "торцбил", "Вилка", "Штекер", R.drawable.a1);
        PusQ("Как называется этот нож?", "Шорный", "Плоский", "Китайский", "Шпатель", R.drawable.a2);
        PusQ("Как называется этот инструмент?", "Сликер", "Бордер", "Краевик", "Бурд", R.drawable.a3);
        PusQ("Токоноле в основном используют как", "средство обработки урезов", "клей", "финишное покрытие", "средство обработки нитей", R.drawable.a4);
        PusQ("Большие отрезы кожи обычно измеряют в квадратных...", "дециметрах", "метрах", "сантиметрах", "дюймах", R.drawable.a5);
        PusQ("Чем нельзя обрабатывать урезы?", "Клей", "Токоноле", "Воск", "Грунт", R.drawable.a6);
        PusQ("Какого вида дубления кожи не существует?", "Бронзовое", "Алюминиевое", "Жировое", "Растительное", R.drawable.a7);
        PusQ("При обработке кожаного изделия обязательно...", "Покрытие урезов грунтом", "Нанесение финишного покрытия", "Использование краски", "Растягивать кожу", R.drawable.a8);
        PusQ("Что из этого списка не является видом кожи?", "пунап", "пулап", "нубук", "байкаст", R.drawable.a9);
        PusQ("Какого типа пробойника не существует?", "Азиатский", "Ромбовидный", "Косой", "Игольчатый", R.drawable.a10);
    }
    void PusQ(String NameQue, String trueV, String V1, String V2,String V3, int idPict){
        MyQuestion newQ=new MyQuestion(NameQue,trueV, V1,V2,V3,idPict);
        questions.add(newQ);
    }
    boolean next(){ //проверка на налие не прорешеных вопросов
        for(int i=0; i<questions.size(); i++){
            if(!questions.get(i).getWas()) return true;
        }
        return false;
    }

    private void next_index(){ //установка нового индекса для прорешивания
        int a=0;
        if(next()){
            while(true){
                a = (int)(Math.random()*questions.size());
                if(!questions.get(a).getWas()) {
                    index=a;
                    break;
                }
            }
        }
    }
    String infoStr(){
        String a=new String();
        a=String.valueOf(size_was+1)+"/"+String.valueOf(questions.size());
        return a;
    }

    MyQuestion start(){ //выбор нового вопроса, изменение и отправка
        next_index();
        //выбрали вопрос
        changeVariantTrue();
        changeVariants();
        //меняем ответы местами
        questions.get(index).setWas();
        //отмечаем что вопрос использовался
        return questions.get(index);
        //отправляем в активити
    }
    private void changeVariantTrue(){ //меняем ответы местами
        Integer b=(int)(Math.random()*4);
        while(questions.get(index).right==b){
            b=(int)(Math.random()*4);
        } //что-бы не было одинаковых чисел

        Collections.swap(questions.get(index).variants, questions.get(index).right , b);
        questions.get(index).right=b;
    }
    private void changeVariants(){ //меняем ответы местами
        Integer a=(int)(Math.random()*4);
        Integer b=(int)(Math.random()*4);
        while(a==b){
            b=(int)(Math.random()*4);
        } //что-бы не было одинаковых чисел
        if(a==questions.get(index).right) questions.get(index).right=b;
        else if(b==questions.get(index).right) questions.get(index).right=a;
        //если вдруг правильный ответ переместили

        Collections.swap(questions.get(index).variants, a, b);
    }

    void end(boolean right){ //получаем результат
        size_was++;
        if(right) size_right++;
    }
}
