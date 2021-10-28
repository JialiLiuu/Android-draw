package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//3分钟免密登录，注册生成pin
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton createbutton,viewbutton;
    String user_pin = "1212";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        createbutton = findViewById(R.id.createButton);
        viewbutton = findViewById(R.id.viewButton);

        createbutton.setOnClickListener(this);
        viewbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.createButton:
                //创建图画
                createPicture();
                break;
            case R.id.viewButton:
                //查看图画
                viewPicture();
                break;
            default:
                break;
        }
    }

    private void createPicture() {
        Intent intent = new Intent(this,CreateWork.class);
        intent.putExtra("user_pin",user_pin);
        MainActivity.this.startActivity(intent);
    }

    private void viewPicture() {
        Intent intent = new Intent(this,ViewWork.class);
        MainActivity.this.startActivity(intent);
    }
}