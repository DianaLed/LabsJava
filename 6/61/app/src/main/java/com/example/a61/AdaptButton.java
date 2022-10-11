package com.example.a61;

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
    private ArrayList<Integer> color_i;
    private ArrayList<String> name_of_color;

    AdaptButton(Context context, int resource, ArrayList<Integer> col, ArrayList<String> name) {
        super(context, resource, name);
        this.name_of_color = name;
        this.color_i = col;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
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
        viewHolder.oneB.setBackgroundColor(color_i.get(position));
        viewHolder.oneB.setText(name_of_color.get(position));
        return convertView;
    }
}
