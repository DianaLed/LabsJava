package com.example.a511;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogScreen {

    public static AlertDialog getDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Счетчик");
        builder.setCancelable(true);
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_activity, null); // Получаем layout по его ID
        builder.setView(view);
        TextView tv= view.findViewById(R.id.textView);
        Button button=view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(Integer.toString(Integer.parseInt(tv.getText().toString())+1));
            }
        });

        tv.setText(Integer.toString(0));
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Отпускает диалоговое окно
            }
        });
        return builder.create();
    }
}
