package com.example.ibb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/23.
 */

public class AddListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public AddListAdapter(Context context, List<String> stringList) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddHolder addHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.add_list_item,null);
            addHolder = new AddHolder();
            addHolder.add_name =convertView.findViewById(R.id.add_name);
            addHolder.add_name2 = convertView.findViewById(R.id.add_name2);
            convertView.setTag(addHolder);
        }else {
            addHolder = (AddHolder) convertView.getTag();
        }

        addHolder.add_name.setText(stringList.get(0));
        Log.e("打印一下适配器里的数据",stringList.get(position));
        addHolder.add_name2.setText(stringList.get(1));
        return convertView;
    }

    class AddHolder{

        TextView add_name;
        TextView add_name2;
    }
}
