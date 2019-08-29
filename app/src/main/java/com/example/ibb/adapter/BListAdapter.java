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

public class BListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public BListAdapter(Context context, List<String> stringList) {
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
        BHolder bHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.b_list_item,null);
            bHolder = new BHolder();
            bHolder.b_title =convertView.findViewById(R.id.b_title);
            convertView.setTag(bHolder);
        }else {
            bHolder = (BHolder) convertView.getTag();
        }

        return convertView;
    }

    class BHolder{

        TextView b_title;
    }
}
