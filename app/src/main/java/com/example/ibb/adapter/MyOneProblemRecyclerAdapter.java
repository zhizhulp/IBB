package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/13.
 */

public class MyOneProblemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TwoBeen> twoBeenList;

    public MyOneProblemRecyclerAdapter(Context context, List<TwoBeen> twoBeenList) {
        this.context = context;
        this.twoBeenList = twoBeenList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        //根据viewType生成viewHolder
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one, null);
                viewHolder = new ViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two, null);
                viewHolder = new ViewHolder1(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three, null);
                viewHolder = new ViewHolder2(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //根据条目的类型给holder中的控件填充数据
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                ViewHolder vh = (ViewHolder) holder;
//                vh.item_title_iv.setText(list.get(position));
//                vh.mImageView.setImageResource(R.drawable.ic_launcher_background);

                break;
            case 1:
                ViewHolder1 vh1 = (ViewHolder1) holder;
//                vh1.item_title_iv.setText(list.get(position));
                break;
            case 2:
                ViewHolder2 vh2 = (ViewHolder2) holder;
//                vh2.item_title_iv.setText(list.get(position));
                break;
        }
    }
    @Override
    public int getItemCount() {
        return twoBeenList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //跟据position对应的条目返回去对应的样式（Type）
        if (position < 4) {
            return 0;
        } else if (4 <= position && position < 6) {
            return 1;
        } else{
            return 2;
        }
    }
}
