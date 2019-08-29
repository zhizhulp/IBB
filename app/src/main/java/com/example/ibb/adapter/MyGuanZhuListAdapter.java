package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/8.
 */

public class MyGuanZhuListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyGuanZhuListAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GuanzhuHolder guanzhuHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.guanzhu_list_item,null);
            guanzhuHolder = new GuanzhuHolder();
            //guanzhuHolder.guanzhu_name_tv = convertView.findViewById(R.id.guanzhu_name_tv);
            convertView.setTag(guanzhuHolder);
        }else {
            guanzhuHolder = (GuanzhuHolder) convertView.getTag();
        }

        guanzhuHolder.guanzhu_name_tv.setText(stringList.get(position));

        return convertView;
    }

    class GuanzhuHolder{
        TextView guanzhu_name_tv;
    }
}
