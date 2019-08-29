package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.custom_view.ImageView_circle;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/15.
 */

public class MyQuanbuListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyQuanbuListAdapter(Context context, List<String> stringList) {
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
        ZuiGaoHolder zuigaoHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.zuigao_list_item,null);
            zuigaoHolder = new ZuiGaoHolder();
            zuigaoHolder.zuigao_head_iv=convertView.findViewById(R.id.zuigao_head_iv);
            zuigaoHolder.title =convertView.findViewById(R.id.title);
            Glide.with(context).load(R.mipmap.b3).transform(new ImageView_circle(context)).into(zuigaoHolder.zuigao_head_iv);
            convertView.setTag(zuigaoHolder);
        }else {
            zuigaoHolder = (ZuiGaoHolder) convertView.getTag();
        }

        zuigaoHolder.title.setText(stringList.get(position));
        return convertView;
    }

    class ZuiGaoHolder{

        ImageView zuigao_head_iv;
        TextView title;
    }
}
