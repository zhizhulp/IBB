package com.example.ibb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/7.
 */

public class MyMessageRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TwoBeen> beenList;
    private MessageHolder messageHolder;

    public MyMessageRecyclerAdapter(Context context, List<TwoBeen> beenList) {
        this.context = context;
        this.beenList = beenList;
    }

    @Override
    public int getItemViewType(int position) {
        int age = beenList.get(position).getAge();
        if (age == 1){
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = View.inflate(context,R.layout.message_list_item,null);
            messageHolder = new MessageHolder(view);
        }else {
            View view = View.inflate(context,R.layout.message_list_item2,null);
            messageHolder = new MessageHolder(view);
        }

        return messageHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 1:
                setTextColor();
                break;
            case 2:
                setTextColorTwo();
                break;
        }
    }

    private void setTextColorTwo() {
        String str = messageHolder.message_tv.getText().toString();
        int bend = "来自杭州的sadoxi".length();
        int fstart = str.indexOf("回答了我的问题");
        int fend = fstart + "回答了我的问题".length();
        int fstart1 = str.indexOf("USB数据线");
        Log.e("TAG", str.substring(fend));
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), fstart1, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        messageHolder.message_tv.setText(style);
    }

    private void setTextColor() {
        String str = messageHolder.message_tv.getText().toString();
        int bend = "来自杭州的sadoxi".length();
        int fstart = str.indexOf("回答了我的问题");
        int fend = fstart + "回答了我的问题".length();
        int fstart1 = str.indexOf("USB数据线");
        Log.e("TAG", str.substring(fend));
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, bend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), fstart1, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        messageHolder.message_tv.setText(style);
    }

    @Override
    public int getItemCount() {
        return beenList.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        ImageView message_header;
        TextView message_tv;

        public MessageHolder(View itemView) {
            super(itemView);
            message_header = itemView.findViewById(R.id.message_header);
            message_tv = itemView.findViewById(R.id.message_tv);
        }
    }
}
