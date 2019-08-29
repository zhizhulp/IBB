package com.example.ibb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/7.
 */

public class MineQuestionListAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public MineQuestionListAdapter(Context context, List<String> stringList) {
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
        QuestionHolder questioneHolder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.question_list_item,null);
            questioneHolder = new QuestionHolder();
            questioneHolder.minequestion_title =convertView.findViewById(R.id.minequestion_title);
            convertView.setTag(questioneHolder);
        }else {
            questioneHolder = (QuestionHolder) convertView.getTag();
        }

        questioneHolder.minequestion_title.setText(stringList.get(position));
        return convertView;
    }

    class QuestionHolder{

        TextView minequestion_title;
    }
}
