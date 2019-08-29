package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/23.
 */

public class MineListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MineListAdapter(Context context, List<String> stringList) {
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
        MineViewHolder mineHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.mine_list_item,null);
            mineHolder = new MineViewHolder();
            mineHolder.mineLv_title =convertView.findViewById(R.id.mineLv_title);
            convertView.setTag(mineHolder);
        }else {
            mineHolder = (MineViewHolder) convertView.getTag();
        }

        mineHolder.mineLv_title.setText(stringList.get(position));
        return convertView;
    }

    class MineViewHolder{

        TextView mineLv_title;
    }
}
