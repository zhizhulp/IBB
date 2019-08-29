package com.example.ibb.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.custom_view.GlideCircleTransform;
import com.example.ibb.custom_view.ImageViewPlus;
import com.example.ibb.entity.HotAnswererBean;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/1/30.
 * 热门答主
 */

public class MyFindRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<HotAnswererBean.DataBean> hotAnswererBeanList;

    private ViewHolder viewHolder;

    public MyFindRecyclerAdapter(Context context, List<HotAnswererBean.DataBean> hotAnswererBeanList) {
        this.context = context;
        this.hotAnswererBeanList = hotAnswererBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.find_recycler_item, parent, false);
        viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(hotAnswererBeanList.get(position).getNickname());
        viewHolder.signature_tv.setText(hotAnswererBeanList.get(position).getSignature());
        //加载圆形图片
        if (!((Activity) context).isFinishing())
            Glide.with(context).load(hotAnswererBeanList.get(position).getPortrait()).transform(new GlideCircleTransform(context)).error(R.mipmap.label_weishenglu).into(viewHolder.find_recycler_tx);

    }

    @Override
    public int getItemCount() {
        return hotAnswererBeanList.size() > 3 ? 3 : hotAnswererBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView find_recycler_tx;
        TextView signature_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            signature_tv = itemView.findViewById(R.id.signature_tv);
            find_recycler_tx = itemView.findViewById(R.id.find_recycler_tx);


        }
    }
}
