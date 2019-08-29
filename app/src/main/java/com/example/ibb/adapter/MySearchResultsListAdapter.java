package com.example.ibb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.entity.SearchBoxBean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/26.
 */

public class MySearchResultsListAdapter extends BaseAdapter {
    private Context context;
    private List<SearchBoxBean.DataBean> searchBoxBeanList;

    public MySearchResultsListAdapter(Context context, List<SearchBoxBean.DataBean> searchBoxBeanList) {
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
        SearchResultHolder searchresultHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.searchresult_list_item,null);
            searchresultHolder = new SearchResultHolder();
            searchresultHolder.searchresult_title = convertView.findViewById(R.id.searchresult_title);
            searchresultHolder.searchresult_score = convertView.findViewById(R.id.searchresult_score);
            searchresultHolder.searchresult_image = convertView.findViewById(R.id.searchresult_image);
            searchresultHolder.searchresult_cb = convertView.findViewById(R.id.searchresult_cb);
            convertView.setTag(searchresultHolder);
        }else {
            searchresultHolder = (SearchResultHolder) convertView.getTag();
        }
        SearchBoxBean.DataBean dataBean = searchBoxBeanList.get(position);
        searchresultHolder.searchresult_title.setText(dataBean.getName());
        searchresultHolder.searchresult_score.setText(dataBean.getScore()+"分");

        try {
            String s = URLEncoder.encode(dataBean.getImgs().get(0).substring(("http://image.ibbtech.cn/image/".length())-1), "utf-8").replaceAll("\\+", "%20");
            Log.e("TAG",s);
            Glide.with(context).load("http://image.ibbtech.cn/image/"+s).error(R.mipmap.ic_launcher).into(searchresultHolder.searchresult_image);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        searchresultHolder.searchresult_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
        return convertView;
    }


    class SearchResultHolder{

        TextView searchresult_title;
        TextView searchresult_score;
        ImageView searchresult_image;
        CheckBox searchresult_cb;
    }
}
