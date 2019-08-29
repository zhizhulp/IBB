package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/16.
 */

public class CListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public CListAdapter(Context context, List<String> stringList) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CHolder cHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.c_list_item,null);
            cHolder = new CHolder();
            cHolder.c_title =convertView.findViewById(R.id.c_title);
            convertView.setTag(cHolder);
        }else {
            cHolder = (CHolder) convertView.getTag();
        }

        return convertView;
    }

    class CHolder{
        TextView c_title;
    }
}
