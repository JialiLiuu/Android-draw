package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewWork extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work);
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
        mRecyclerView.setAdapter(mAdapter = new Adapter(this));
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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_work);
//        GetImagesPath();
//        Log.i("GetImagesPath", "onCreate: listItems.size " + listItems.size());
//
//        lists = new ArrayList<>();
//        for (int i = 0; i < theme.length; i++) {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("ItemImage", "/storage/emulated/0/Pictures/1635314041098.jpg");//加入图片
//            map.put("ItemTitle", "第"+i+"行");
//            map.put("ItemText", "这是第"+i+"行");
//            lists.add(map);
//        }
//        listView = (ListView)findViewById(R.id.lv);
//        //适配器指定应用自己定义的xml格式
//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,lists,//需要绑定的数据
//                R.layout.item_picture,//每一行的布局
//                //动态数组中的数据源的键对应到定义布局的View中
//                new String[] {"ItemImage","ItemTitle", "ItemText"},
//                new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText});
//
//        listView.setAdapter(mSimpleAdapter);
//    }

//    void GetImagesPath(){
//
//        paths = new ArrayList();
//        names = new ArrayList();
//
//        Cursor cursor = getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//        while (cursor.moveToNext()) {
//            //获取图片的名称
//            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//            // 获取图片的绝对路径
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            String path = cursor.getString(column_index);
//
//
//            paths.add(path);
//            names.add(name);
//
//            Log.i("GetImagesPath", "GetImagesPath: name = "+name+"  path = "+ path);
//
//
//        }
//        listItems = new ArrayList<>();
//        for (int i = 0; i < paths.size(); i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("name", paths.get(i));
//            map.put("desc", names.get(i));
//            listItems.add(map);
//        }
//    }
}