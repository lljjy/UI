package com.example.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Menu3 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }


    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        textView=(TextView)findViewById(R.id.text);
        switch (item.getItemId()) {
            case R.id.submenu_one:
                textView.setTextSize(10);
                break;
            case R.id.submenu_two:
                textView.setTextSize(16);
                break;
            case R.id.submenu_three:
                textView.setTextSize(20);
                break;
            case R.id.menu_two:
                Toast.makeText(this, "你点击了普通菜单项", Toast.LENGTH_LONG).show();
                break;
            case R.id.black:
                textView.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case R.id.red:
                textView.setTextColor(this.getResources().getColor(R.color.red));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
