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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton createbutton,viewbutton;
    int index;

     int[] mThumbIds={//显示的图片数组
//             R.drawable.bg_1,R.drawable.bg_1,R.drawable.bg_1,R.drawable.bg_1,R.drawable.bg_1,R.drawable.bg_1,
//             R.drawable.bg_1,R.drawable.bg_1,R.drawable.bg_1

            R.drawable.bg_1,R.drawable.bg_2,
            R.drawable.bg_3,R.drawable.bg_4,
            R.drawable.bg_5,R.drawable.bg_6,
            R.drawable.bg_7,R.drawable.bg_8,
            R.drawable.bg_9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        List<Bitmap> list1=new LinkedList<>();;
        for(int i=0;i<9;i++){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(),mThumbIds[i],options);

            options.inSampleSize = calculateSampleSize(300,300,options.outWidth,options.outHeight);
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inJustDecodeBounds = false;
            Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), mThumbIds[i], options);
            list1.add(mBitmap);
        }

        GridView gridview=(GridView)findViewById(R.id.gridview);//找到main.xml中定义gridview 的id
        //final ImageAdapter imageAdapter=new ImageAdapter(this, mThumbIds);
        final ImageAdapter imageAdapter=new ImageAdapter(this, list1);
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

    private int calculateSampleSize(int targetWidth,int targetHeight,int nowWidth,int nowHeight){
        Log.e("压缩", "未压缩前 宽 = "+nowWidth+",未压缩钱高 = "+nowHeight);
        int result = 1;
        int widthSize = nowWidth%targetWidth == 0? nowWidth/targetWidth:nowWidth/targetWidth+1;
        int heightSize = nowHeight%targetHeight == 0? nowHeight%targetHeight:nowHeight/targetHeight+1;
        int maxSize = Math.max(widthSize,heightSize);
        if (maxSize >= 1){
            result = maxSize;
        }

        if (nowHeight > targetHeight || nowWidth > targetWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) nowHeight / (float) targetHeight);
            final int widthRatio = Math.round((float) nowWidth / (float) targetWidth);
            // 选择宽和高比率中最小的作为inSampleSize的值，这样可以保证最终生成图片的宽和高
            // 一定都会大于等于目标的宽和高。
            result = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        Log.e("压缩", "比率 = "+result);
        return result;
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