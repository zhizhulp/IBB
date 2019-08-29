package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/1/23.
 */

public class MineRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;

    public MineRecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_recycler_item,parent,false);

        MyRecyclerHolder recyclerHolder = new MyRecyclerHolder(view);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRecyclerHolder recyclerHolder= (MyRecyclerHolder) holder;
        recyclerHolder.textView.setText(list.get(0));
        recyclerHolder.textView.setText(list.get(1));
        recyclerHolder.textView.setText(list.get(2));
        recyclerHolder.textView.setText(list.get(3));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyRecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyRecyclerHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
