package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/16.
 */

public class DRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TwoBeen> twoBeenList;
    private DHolder dHolder;

    public DRecyclerAdapter(Context context, List<TwoBeen> twoBeenList) {
        this.context = context;
        this.twoBeenList = twoBeenList;
    }

    @Override
    public int getItemViewType(int position) {
        int age = twoBeenList.get(position).getAge();
        if (age == 1){
            return 1;
        }else {
            return 2;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = View.inflate(context, R.layout.d_recycler_item,null);
            dHolder = new DHolder(view);
        }else {
            View view = View.inflate(context,R.layout.d_recycler_item2,null);
            dHolder = new DHolder(view);
        }

        return dHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        dHolder= (DHolder) holder;

    }

    @Override
    public int getItemCount() {
        return twoBeenList.size();
    }

    class DHolder extends RecyclerView.ViewHolder{
        TextView d_tv;
        TextView d_title;
        public DHolder(View itemView) {
            super(itemView);
            d_tv = itemView.findViewById(R.id.d_tv);
            d_title = itemView.findViewById(R.id.d_title);
        }
    }
}
