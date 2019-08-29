package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/8.
 */

public class MyDetailsRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TwoBeen> list;
    private DetailsRecyclerHolder detailsHolder;

    public MyDetailsRecyclerAdapter(Context context, List<TwoBeen> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        int age = list.get(position).getAge();
        if (age == 1){
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = View.inflate(context, R.layout.details_recycler_item,null);
            detailsHolder = new DetailsRecyclerHolder(view);
        }else {
            View view = View.inflate(context,R.layout.details_recycler_item2,null);
            detailsHolder = new DetailsRecyclerHolder(view);
        }

        return detailsHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        detailsHolder = (DetailsRecyclerHolder) holder;
        detailsHolder.details_title.setText(list.get(position).getName());
        detailsHolder.details_nr.setText(list.get(position).getSex());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DetailsRecyclerHolder extends RecyclerView.ViewHolder{
        TextView details_title;
        TextView details_nr;
        public DetailsRecyclerHolder(View itemView) {
            super(itemView);

            details_title=itemView.findViewById(R.id.details_title);
            details_nr=itemView.findViewById(R.id.details_nr);
        }
    }
}
