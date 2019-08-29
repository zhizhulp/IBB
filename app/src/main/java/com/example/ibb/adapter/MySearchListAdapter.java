package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/17.
 */

public class MySearchListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MySearchListAdapter(Context context, List<String> stringList) {
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
        SearchHolder searchHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.search_list_item,null);
            searchHolder = new SearchHolder();
            searchHolder.search_title =convertView.findViewById(R.id.search_title);
            convertView.setTag(searchHolder);
        }else {
            searchHolder = (SearchHolder) convertView.getTag();
        }

        searchHolder.search_title.setText(stringList.get(position));
        return convertView;
    }

    class SearchHolder{

        TextView search_title;
    }
}
