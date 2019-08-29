package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.custom_view.GlideCircleTransform;
import com.example.ibb.entity.LatestBean;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/13.
 * * 发现模块最新问题（查看更多）
 */

public class MyFindMoreListAdapter extends BaseAdapter {
    private Context context;
    List<LatestBean.DataBean> datalist;

    public MyFindMoreListAdapter(Context context, List<LatestBean.DataBean> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FindMoreViewHolder findMoreHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.findmore_list_item,null);
            findMoreHolder = new FindMoreViewHolder();
            findMoreHolder.findmore_name_tv =convertView.findViewById(R.id.findmore_name_tv);
            findMoreHolder.findmore_title_tv =convertView.findViewById(R.id.findmore_title_tv);
            findMoreHolder.findmore_header=convertView.findViewById(R.id.findmore_header);

            convertView.setTag(findMoreHolder);
        }else {
            findMoreHolder = (FindMoreViewHolder) convertView.getTag();
        }

        findMoreHolder.findmore_name_tv.setText(datalist.get(position).getUser().getNickname());
        findMoreHolder.findmore_title_tv.setText(datalist.get(position).getTitle());
        //加载圆形图片
        Glide.with(context).load(datalist.get(position).getUser().getPortrait()).transform(new GlideCircleTransform(context)).error(R.mipmap.label_weishenglu).into(findMoreHolder.findmore_header);
        return convertView;
    }

    class FindMoreViewHolder{
        TextView findmore_title_tv;
        TextView findmore_name_tv;
        ImageView findmore_header;
    }
}
