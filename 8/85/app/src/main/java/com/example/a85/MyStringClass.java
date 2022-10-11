package com.example.a85;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyStringClass {
    private final ArrayList<String> list;

    public MyStringClass() {
        list = new ArrayList<>();
    }

    public MyStringClass(ArrayList<String> list) {
        this.list = list;
    }

    public void add(String str) {
        list.add(str);
    }

    public void dellLast() {
        if (list.size() > 0) list.remove(list.size() - 1);
    }

    public String getAllElemInOneStr() {
        if(list.size() == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i)).append(", ");
        }
        builder.append(list.get(list.size() - 1));
        builder.setCharAt(0, Character.toUpperCase(builder.charAt(0)));
        return builder.toString();
    }
}
