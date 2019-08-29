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
import com.example.ibb.entity.HotAnswererBean;
import com.example.ibb.entity.LatestBean;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/12.
 *发现模块热门答主（查看更多）
 */

public class MyMoreListAdapter extends BaseAdapter {
    private Context context;
    private List<HotAnswererBean.DataBean> hotmoreBeanList;

    public MyMoreListAdapter(Context context, List<HotAnswererBean.DataBean> hotmoreBeanList) {
        this.context = context;
        this.hotmoreBeanList = hotmoreBeanList;
    }

    @Override
    public int getCount() {
        return hotmoreBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotmoreBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MoreHolder moreHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.more_list_item,null);
            moreHolder = new MoreHolder();
            moreHolder.more_name_tv = convertView.findViewById(R.id.more_name_tv);
            moreHolder.more_signature_tv = convertView.findViewById(R.id.more_signature_tv);
            moreHolder.more_tx_iv = convertView.findViewById(R.id.more_tx_iv);
            convertView.setTag(moreHolder);
        }else {
            moreHolder = (MoreHolder) convertView.getTag();
        }

        moreHolder.more_name_tv.setText(hotmoreBeanList.get(position).getNickname());
        moreHolder.more_signature_tv.setText(hotmoreBeanList.get(position).getSignature());
        //加载圆形图片
        Glide.with(context).load(hotmoreBeanList.get(position).getPortrait()).transform(new GlideCircleTransform(context)).error(R.mipmap.label_weishenglu).into(moreHolder.more_tx_iv);
        return convertView;
    }

    class MoreHolder{
        TextView more_name_tv;
        TextView more_signature_tv;
        ImageView more_tx_iv;
    }
}
