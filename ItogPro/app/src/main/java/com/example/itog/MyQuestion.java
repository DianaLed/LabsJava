package com.example.itog;

import java.util.ArrayList;

public class MyQuestion {
    public String NameOfQ;
    public ArrayList<String> variants;
    int idPictures; //=R.drawable.fon;
    private boolean was;
    int right;

    MyQuestion(String NameQue, String trueV, String V1, String V2,String V3, int idPict){
        variants=new ArrayList<String>();
        variants.add(trueV);
        variants.add(V1);
        variants.add(V2);
        variants.add(V3);
        NameOfQ=NameQue;
        idPictures=idPict;
        was=false;
        right=0;
    }
    void setWas(){
    was=true;
    }
    boolean getWas(){
        return was;
    }
}
