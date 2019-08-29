package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/14.
 */

public class MyYiProblemRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TwoBeen> twoBeenList;
    private YiHolder yiHolder;

    public MyYiProblemRecyclerAdapter(Context context, List<TwoBeen> twoBeenList) {
        this.context = context;
        this.twoBeenList = twoBeenList;
    }

    @Override
    public int getItemViewType(int position) {
        int age = twoBeenList.get(position).getAge();
        if (age == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = View.inflate(context, R.layout.item_one,null);
            yiHolder = new YiHolder(view);
        }else {
            View view = View.inflate(context,R.layout.item_two,null);
            yiHolder = new YiHolder(view);
        }

        return yiHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        yiHolder = (YiHolder) holder;
    }

    @Override
    public int getItemCount() {
        return twoBeenList.size();
    }

    class YiHolder extends RecyclerView.ViewHolder{

        public YiHolder(View itemView) {
            super(itemView);
        }
    }
}
