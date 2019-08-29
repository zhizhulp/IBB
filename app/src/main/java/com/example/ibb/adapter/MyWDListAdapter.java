package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/11.
 */

public class MyWDListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyWDListAdapter(Context context, List<String> stringList) {
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
        WDHolder wdHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.wd_list_item,null);
            wdHolder = new WDHolder();
            wdHolder.wd_title_tv =convertView.findViewById(R.id.wd_title_tv);
            convertView.setTag(wdHolder);
        }else {
            wdHolder = (WDHolder) convertView.getTag();
        }
        wdHolder.wd_title_tv.setText(stringList.get(position));

        return convertView;
    }
    class WDHolder{
        TextView wd_title_tv;
    }
}
