package com.example.ibb.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.entity.HighBean;
import com.example.ibb.entity.HotAnswererBean;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.NumberFormatUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class MyFindListViewAdapter2 extends BaseAdapter {
    private Context context;
    private List<HighBean.DataBean> highBeanList;

    public MyFindListViewAdapter2(Context context, List<HighBean.DataBean> highBeanList) {
        this.context = context;
        this.highBeanList = highBeanList;
    }

    @Override
    public int getCount() {
        return highBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return highBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FindViewHolder2 viewHolder2;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item2, null);
            viewHolder2 = new FindViewHolder2();
            viewHolder2.high_iv = convertView.findViewById(R.id.high_iv);
            viewHolder2.item2_title = convertView.findViewById(R.id.item2_title);
            viewHolder2.item2_score = convertView.findViewById(R.id.item2_score);
            viewHolder2.item2_minprice = convertView.findViewById(R.id.item2_minprice);
            viewHolder2.item2_maxprice = convertView.findViewById(R.id.item2_maxprice);
            viewHolder2.ratingBar = convertView.findViewById(R.id.rating_bar);
            convertView.setTag(viewHolder2);
        } else {
            viewHolder2 = (FindViewHolder2) convertView.getTag();
        }
        //加载图片

         Picasso.with(context).load(highBeanList.get(position).getImgs().get(0))
                 .placeholder(R.mipmap.b3)
                 .error(R.mipmap.label_jiazaitupian).into(viewHolder2.high_iv);
        viewHolder2.item2_title.setText(highBeanList.get(position).getName());
        viewHolder2.item2_score.setText(NumberFormatUtils.getNewDouble(highBeanList.get(position).getScore()) + "分");
        viewHolder2.item2_minprice.setText(highBeanList.get(position).getMinPrice() + "");
        viewHolder2.item2_maxprice.setText(highBeanList.get(position).getMaxPrice() + "");
        viewHolder2.ratingBar.setRating((float) (highBeanList.get(position).getScore()*5/10));
        return convertView;
    }

    class FindViewHolder2 {
        ImageView high_iv;
        TextView item2_title;
        TextView item2_score;
        TextView item2_minprice;
        TextView item2_maxprice;
        RatingBar ratingBar;
    }
}
