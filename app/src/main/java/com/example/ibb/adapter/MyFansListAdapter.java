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

public class MyFansListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyFansListAdapter(Context context, List<String> stringList) {
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
        FinsHolder finsHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.fins_list_item,null);
            finsHolder = new FinsHolder();
            //finsHolder.fans_name_tv =convertView.findViewById(R.id.fans_name_tv);
            convertView.setTag(finsHolder);
        }else {
            finsHolder = (FinsHolder) convertView.getTag();
        }
        return convertView;
    }
    class FinsHolder{

        TextView fans_name_tv;
    }
}
