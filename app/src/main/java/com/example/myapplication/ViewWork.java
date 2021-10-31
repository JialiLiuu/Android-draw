package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewWork extends AppCompatActivity {

    private String TAG = "展示图片列表的Activity";

    ArrayList paths = null;
    ArrayList names= null;
    List<Map<String, Object>> listItems;

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work);

        GetImagesPath();
        Log.i("GetImagesPath", "onCreate: listItems.size " + listItems.size());

        initView();//调用初始化控件方法
        setAdapter();//调用设置适配器方法
    }

    /**
     * 设置适配器方法
     */
    private void setAdapter() {
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        mRecyclerView.setAdapter(mAdapter = new Adapter(this, listItems));
        //设置列表中子项的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    /**
     * 初始化控件
     */
    private void initView() {
        //获取列表控件
        mRecyclerView = findViewById(R.id.lv);
    }

    void GetImagesPath(){

        paths = new ArrayList();
        names = new ArrayList();

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取图片的名称
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            // 获取图片的绝对路径
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(column_index);


            paths.add(path);
            names.add(name);

            Log.i(TAG, "图片路径是"+ path);
        }
        listItems = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("path", paths.get(i));
            map.put("name", names.get(i));
            listItems.add(map);
        }
    }
}