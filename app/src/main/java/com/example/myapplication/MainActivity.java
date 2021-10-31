package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//3分钟免密登录，注册生成pin
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton createbutton,viewbutton;
    String user_pin = "1212";
    int index;

     int[] mThumbIds={//显示的图片数组

            R.drawable.tt,R.drawable.tt,
            R.drawable.tt,R.drawable.bg,
            R.drawable.bg,R.drawable.bg,
            R.drawable.bg,R.drawable.bg,
            R.drawable.bg
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        GridView gridview=(GridView)findViewById(R.id.gridview);//找到main.xml中定义gridview 的id
        final ImageAdapter imageAdapter=new ImageAdapter(this, mThumbIds);
        gridview.setAdapter(imageAdapter);//调用ImageAdapter.java
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){//监听事件
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                index=position;
                imageAdapter.changeState(position);
                //Toast.makeText(MainActivity.this, ""+position,Toast.LENGTH_SHORT).show();//显示信息;
            }
        });
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
        intent.putExtra("code",1);
        intent.putExtra("background",mThumbIds[index]);
        MainActivity.this.startActivity(intent);
    }

    private void viewPicture() {
        Intent intent = new Intent(this,ViewWork.class);
        MainActivity.this.startActivity(intent);
    }
}