package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.entity.SearchBoxBean;
import com.example.ibb.utils.NumberFormatUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/8.
 */

public class MyDetailsMoreListAdapter extends BaseAdapter {
    private Context context;
    private List<SearchBoxBean.DataBean> searchBoxBeanList;

    public MyDetailsMoreListAdapter(Context context, List<SearchBoxBean.DataBean> searchBoxBeanList) {
        this.context = context;
        this.searchBoxBeanList = searchBoxBeanList;
    }

    @Override
    public int getCount() {
        return searchBoxBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchBoxBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailsMoreHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.detailsmore_list_item,null);
            viewHolder = new DetailsMoreHolder();
            viewHolder.title =convertView.findViewById(R.id.tv_name);
            viewHolder.ratingBar =convertView.findViewById(R.id.rating_bar);
            viewHolder.score =convertView.findViewById(R.id.tv_score);
            viewHolder.price =convertView.findViewById(R.id.tv_price);
            viewHolder.imIcon =convertView.findViewById(R.id.im_icon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (DetailsMoreHolder) convertView.getTag();
        }
        SearchBoxBean.DataBean dataBean = searchBoxBeanList.get(position);
        viewHolder.title.setText(dataBean.getName());
        viewHolder.ratingBar.setRating((float) (dataBean.getScore()*5/10));
        viewHolder.score.setText(NumberFormatUtils.getNewDouble(dataBean.getScore())+"分");
        viewHolder.price.setText("￥"+dataBean.getMinPrice()+"-"+dataBean.getMaxPrice());
        try {
            String s = URLEncoder.encode(searchBoxBeanList.get(position).getImgs().get(0).substring(("http://image.ibbtech.cn/image/".length())-1), "utf-8").replaceAll("\\+", "%20");
            Glide.with(context).load("http://image.ibbtech.cn/image/"+s).error(R.mipmap.label_jiazaitupian).into(viewHolder.imIcon);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    class DetailsMoreHolder{
        ImageView imIcon;
        TextView title;
        RatingBar ratingBar;
        TextView score;
        TextView price;
    }
}
