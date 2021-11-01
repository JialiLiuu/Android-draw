package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.GridView;

import java.util.List;


public class ImageAdapter extends BaseAdapter {

    int lastPosition;
    int[] status={0,1,1,1,1,1,1,1,1};
    private Context mContext;
//    int[] mThumbIds;
    List<Bitmap> mThumbIds;

    public ImageAdapter(Context c, List<Bitmap> mThumbIds)
//    public ImageAdapter(Context c,int[] mThumbIds)
    {
        mContext=c;
        this.mThumbIds=mThumbIds;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 9;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ImageView imageview;
        if(convertView==null)
        {
            imageview=new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            imageview=(ImageView) convertView;
        }
        //imageview.setImageResource(mThumbIds[position]);
        imageview.setImageBitmap(mThumbIds.get(position));
        if(status[position]==1){
            imageview.setBackgroundResource(R.drawable.background_before);
        }
        else{
            imageview.setBackgroundResource(R.drawable.background_border);
        }
        imageview.setPadding(8,8,8,8);

        return imageview;
    }

    public void changeState(int position) {

        if (lastPosition != -1) {
            status[lastPosition]=1;
        }
        status[position]=0;
        lastPosition = position; // 记录本次选中的位置
        notifyDataSetChanged(); // 通知适配器进行更新

    }
}