package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.entity.GeeksBean;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/26.
 */

public class MyPopulargeeksListAdapter extends BaseAdapter {
    private Context context;
    private List<GeeksBean> geeksBeanList;
//    private List<String> stringlist = new ArrayList<>();

//    public MyPopulargeeksListAdapter(Context context, List<String> stringlist) {
//        this.context = context;
//        this.stringlist = stringlist;
//    }

    public MyPopulargeeksListAdapter(Context context, List<GeeksBean> geeksBeanList) {
        this.context = context;
        this.geeksBeanList = geeksBeanList;
    }

    @Override
    public int getCount() {
        return geeksBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return geeksBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PopularGeeksHolder geeksHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.geeks_list_item,null);
            geeksHolder = new PopularGeeksHolder();
            geeksHolder.populargeeks_title =convertView.findViewById(R.id.populargeeks_title);
//            geeksHolder.geeks_rb = convertView.findViewById(R.id.geeks_rb);
            convertView.setTag(geeksHolder);
        }else {
            geeksHolder = (PopularGeeksHolder) convertView.getTag();
        }

//        geeksHolder.populargeeks_title.setText(stringlist.get(position));
//        final GlobalValue globalValue = new GlobalValue();
//        geeksHolder.geeks_rb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                boolean ischeck = globalValue.isCheck();
//                if (ischeck){
//                    if (v ==geeksHolder.geeks_rb)geeksHolder.geeks_rb.setChecked(false);
//                }else {
//                    if (v ==geeksHolder.geeks_rb)geeksHolder.geeks_rb.setChecked(true);
//                }
//                globalValue.setCheck(!ischeck);
//            }
//        });
        return convertView;
    }

    class PopularGeeksHolder{
         TextView populargeeks_title;
//            RadioButton geeks_rb;
    }

    private class GlobalValue {
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private boolean isCheck;
    }
}
