package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/15.
 */

public class MyRelatedProductsListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MyRelatedProductsListAdapter(Context context, List<String> stringList) {
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
        RelatedProductHolder relatedProductHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.product_list_item,null);
            relatedProductHolder = new RelatedProductHolder();
            relatedProductHolder.title =convertView.findViewById(R.id.title);
            convertView.setTag(relatedProductHolder);
        }else {
            relatedProductHolder = (RelatedProductHolder) convertView.getTag();
        }
        relatedProductHolder.title.setText(stringList.get(position));

        return convertView;
    }

    class RelatedProductHolder{
        TextView title;
    }
}
