package com.example.ibb.adapter;

import android.app.Activity;
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
import com.example.ibb.ui.ui.answered.AnswerDetActivity;
import com.example.ibb.ui.ui.answered.MineAnsweredActivity;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class MyFindListViewAdapter extends BaseAdapter {
    private Context context;
    private List<LatestBean.DataBean> latestBeanList;


    public MyFindListViewAdapter(Context context, List<LatestBean.DataBean> latestBeanList) {
        this.context = context;
        this.latestBeanList = latestBeanList;
    }

    //这里的getCount方法是程序在加载显示到ui上时就要先读取的，这里获得的值决定了listview显示多少行
    @Override
    public int getCount() {
        return latestBeanList.size() > 3 ? 3 : latestBeanList.size();
    }

    //根据ListView所在位置返回View
    @Override
    public Object getItem(int position) {
        return latestBeanList.get(position);
    }

    //根据ListView位置得到数据源集合中的Id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FindViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            viewHolder = new FindViewHolder();
            viewHolder.find_title = convertView.findViewById(R.id.find_title);
            viewHolder.find_tx = convertView.findViewById(R.id.find_tx);
            viewHolder.find_content = convertView.findViewById(R.id.find_content);
            viewHolder.find_time = convertView.findViewById(R.id.find_time);
            viewHolder.tvGoAnswer = convertView.findViewById(R.id.tv_go_answer);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FindViewHolder) convertView.getTag();
        }
        viewHolder.tvGoAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineAnsweredActivity.startAnsApply(context,latestBeanList.get(position).getId(),110);
            }
        });
        viewHolder.find_title.setText(latestBeanList.get(position).getUser().getNickname());
        viewHolder.find_content.setText(latestBeanList.get(position).getTitle());
        //加载圆形图片
        if (!((Activity) context).isFinishing())
            Picasso.with(context).load(latestBeanList.get(position).getUser().getPortrait())
                    .placeholder(R.mipmap.b3).error(R.mipmap.b3).into(viewHolder.find_tx);
        //获取时间
        viewHolder.find_time.setText(TimeUtils.getTimeFormatText(latestBeanList.get(position).getTime()));

        return convertView;
    }

    class FindViewHolder {
        TextView find_title;
        ImageView find_tx;
        TextView find_content;
        TextView find_time;
        TextView tvGoAnswer;
    }
}
