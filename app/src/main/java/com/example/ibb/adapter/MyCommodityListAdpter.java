package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/7.
 */

public class MyCommodityListAdpter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyCommodityListAdpter(Context context, List<String> stringList) {
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
        CommodityViewHolder viewHolder;
//        if (convertView == null){
//            convertView = View.inflate(context, R.layout.commodity_list_item,null);
//            viewHolder = new CommodityViewHolder();
//            viewHolder.commodity_title =convertView.findViewById(R.id.commodity_title);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (CommodityViewHolder) convertView.getTag();
//        }
//
//        viewHolder.commodity_title.setText(stringList.get(position));
        return convertView;
    }

    class CommodityViewHolder{
        TextView commodity_title;
    }
}
