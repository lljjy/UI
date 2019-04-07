package com.example.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MyDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog dialog;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_dialog, null);
        dialog=new AlertDialog.Builder(this)
                .setView(layout).create();
        dialog.show();
        Button sign=(Button)layout.findViewById(R.id.sign);
        final Button cancel=(Button)layout.findViewById(R.id.cancel);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyDialog.this,"welcome",Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog(R.id.cancel);
            }
        });


    }
}
