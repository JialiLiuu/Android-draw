package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private String TAG = "展示图片列表的Adapter";

    private Context lContent;//定义上下文

    //集合
    private List<String> listIcon = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<String> listInfo = new ArrayList<>();

    public Adapter(Context lContent, List<Map<String, Object>> listItems) {
        this.lContent = lContent;
        //设置菜单行数与行内图标、名称、信息
        for (Map<String, Object> map:listItems) {
            listIcon.add(map.get("path").toString());
            listName.add(map.get("name").toString());
            listInfo.add("编辑");
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取列表中每行的布局文件
        View view = LayoutInflater.from(lContent).inflate(R.layout.item_picture, parent, false);
        return new MyViewHolder(view);
    }

    //设置列表中行所显示的内容
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        //设置图标
        File file = new File(listIcon.get(position));
        //将bitmap转化成drawable
        Bitmap bmp = null;
        try {
            bmp = MediaStore.Images.Media.getBitmap(lContent.getContentResolver(), Uri.fromFile(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.img.setImageBitmap(bmp);

        //设置图片名称
        holder.name.setText(""+listName.get(position));
        //设置图片
        holder.info.setText(""+listInfo.get(position));
        //设置内容宽度为屏幕的宽度
        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(lContent);

        //删除按钮的方法
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();//获取要删除行的位置
                removeData(n);//删除列表中指定的行
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();//获取要放大行的位置
                File file = new File(listIcon.get(n));
                //将bitmap转化成drawable
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(lContent.getContentResolver(), Uri.fromFile(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bigImageLoader(bitmap);//放大列表中指定的行
            }
        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();//需要编辑的位置
                Intent intent = new Intent(lContent,CreateWork.class);
                intent.putExtra("code",2);
                intent.putExtra("background",listIcon.get(n));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                lContent.startActivity(intent);
                if (ViewWork.class.isInstance(lContent)) {
                    // 转化为activity，然后finish就行了
                    ViewWork activity = (ViewWork) lContent;
                    activity.finish();
                }
            }
        });
    }

    private void bigImageLoader(Bitmap bitmap){
        final Dialog dialog = new Dialog(lContent);
        ImageView image = new ImageView(lContent);
        image.setImageBitmap(bitmap);
        dialog.setContentView(image);
        //将dialog周围的白块设置为透明
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //显示
        dialog.show();
        //点击图片取消
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.cancel();
            }
        });
    }

    //返回行的总数
    @Override
    public int getItemCount() {
        return listIcon.size();
    }

    //删除列表行中信息的方法
    public void removeData(int position){
        File file = new File(listIcon.get(position));
        if (file.isFile() && file.exists()) {
            file.delete();
            Log.i(TAG,"图片删除成功");
        }
        else{
            Log.i(TAG,"图片删除失败");
        }
        listIcon.remove(position);//删除图标
        listName.remove(position);//删除行中名字
        listInfo.remove(position);//删除信息
        notifyItemRemoved(position);//删除行
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView btn_delete;
        public TextView name, info;//名字与信息
        public ImageView img;//图标
        public ViewGroup layout_content;//图标与信息布局

        //获取控件
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ItemTitle);
            info = itemView.findViewById(R.id.ItemText);
            img = itemView.findViewById(R.id.ItemImage);
            layout_content = itemView.findViewById(R.id.layout_content);
            btn_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}