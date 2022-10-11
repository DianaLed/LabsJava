package com.example.a62lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class AdaptButton extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<String> nameOfElem;
    MainActivity mainActivity;
    AdaptButton(Context context, int resource, ArrayList<String> name, MainActivity ma) {
        super(context, resource, name);
        this.nameOfElem = name;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.mainActivity=ma;
    }

    private class ViewHolder {
        final Button oneB;
        ViewHolder(View view){
            oneB = view.findViewById(R.id.button_lv);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.oneB.setText(nameOfElem.get(position));
        viewHolder.oneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.ClickOnViev(position);
            }
        });
        return convertView;
    }
}
