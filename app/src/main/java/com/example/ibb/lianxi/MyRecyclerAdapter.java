package com.example.ibb.lianxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/15.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> stringList;

    public MyRecyclerAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    /*
            上面的ViewHolder是这个适配器必要的泛型，必须有。布局里有几种Type，下面就要写几个自定义的
            ViewHlder，这些自定义的ViewHolder都要继承于RecyclerView.ViewHolder。
        */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        //根据viewType生成viewHolder
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_a, null);
                viewHolder = new Item_one(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_b, null);
                viewHolder = new Item_two(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_c, null);
                viewHolder = new Item_thread(view);
                break;
        }
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //根据条目的类型给holder中的控件填充数据
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                Item_one it1 = (Item_one) holder;
                it1.my_texta.setText("你有哪些口紅想看張開雅繁體字1");
                it1.my_textaa.setText("TA：女神的必需品，想要美麗的小仙女");

                break;
            case 1:
                Item_two it2 = (Item_two) holder;
                it2.my_imgb.setImageResource(R.mipmap.label_kouhongtu);
                it2.my_textb.setText("你有哪些想買卻捨不得下單的化妝品在糾結2");
                break;
            case 2:
                Item_thread it3 = (Item_thread) holder;
                it3.my_textc.setText("這個佈局好複雜3");
                break;
        }
    }
    @Override
    public int getItemCount() {
        //获取条目数，模拟数据，这里是写死的
        return 5;
    }
    @Override
    public int getItemViewType(int position) {
        //跟据position对应的条目返回去对应的样式（Type）
        if (position < 2) {
            return 0;
        } else if (2<= position && position < 3) {
            return 1;
        } else return 2;
    }
}

