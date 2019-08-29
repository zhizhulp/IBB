package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.entity.SearchBoxBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/9.
 * 相关商品ListAdapter
 */

public class MySearchDetailsListAdapter extends BaseAdapter{
    private Context context;
    private List<SearchBoxBean.DataBean> searchBoxBeanList;

    public MySearchDetailsListAdapter(Context context, List<SearchBoxBean.DataBean> searchBoxBeanList) {
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
        SearchDetailsHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.search_details_list_item,null);
            viewHolder = new SearchDetailsHolder();
            viewHolder.searchdetails_iv = convertView.findViewById(R.id.searchdetails_iv);
            viewHolder.searchdetails_title = convertView.findViewById(R.id.searchdetails_title);
            viewHolder.searchdetails_score = convertView.findViewById(R.id.searchdetails_score);
            viewHolder.searchdetails_minprice = convertView.findViewById(R.id.searchdetails_minprice);
            viewHolder.searchdetails_maxprice = convertView.findViewById(R.id.searchdetails_maxprice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (SearchDetailsHolder) convertView.getTag();
        }
        //加载图片
            try {
                String s = URLEncoder.encode(searchBoxBeanList.get(position).getImgs().get(0).substring(("http://image.ibbtech.cn/image/".length())-1), "utf-8").replaceAll("\\+", "%20");
                Glide.with(context).load("http://image.ibbtech.cn/image/"+s).error(R.mipmap.label_jiazaitupian).into(viewHolder.searchdetails_iv);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            viewHolder.searchdetails_title.setText(searchBoxBeanList.get(position).getName());
            viewHolder.searchdetails_score.setText(searchBoxBeanList.get(position).getScore() + "分");
            viewHolder.searchdetails_minprice.setText(searchBoxBeanList.get(position).getMinPrice() + "");
            viewHolder.searchdetails_maxprice.setText(searchBoxBeanList.get(position).getMaxPrice() + "");
        return convertView;
    }
    class SearchDetailsHolder{
        ImageView searchdetails_iv;
        TextView searchdetails_title;
        TextView searchdetails_score;
        TextView searchdetails_minprice;
        TextView searchdetails_maxprice;
    }
}
