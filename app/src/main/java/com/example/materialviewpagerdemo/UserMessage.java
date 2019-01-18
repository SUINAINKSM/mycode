package com.example.materialviewpagerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import   java.text.SimpleDateFormat;
import java.util.Date;

public class UserMessage extends AppCompatActivity {
   public  SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
    private RecyclerView mRecyclerView;
    public void getCurrentTime(){
        Date curDate =  new Date(System.currentTimeMillis());
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        List<ImageInfor> list = new ArrayList<>();
        list.add(new ImageInfor(R.mipmap.caiyilin, "蔡依林"));
        list.add(new ImageInfor(R.mipmap.ulinxinru, "林心如"));
        list.add(new ImageInfor(R.mipmap.caiyilin,"蔡依林"));
        list.add(new ImageInfor(R.mipmap.ulinxinru, "林心如"));
        list.add(new ImageInfor(R.mipmap.caiyilin,"蔡依林"));
        list.add(new ImageInfor(R.mipmap.ulinxinru, "林心如"));


    }
}
