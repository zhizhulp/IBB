package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibb.R;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/13.
 */

public class MyRelatedAnswerdRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TwoBeen> beenList;
    private RelatedAnswerdRecyclerHolder relatedanswerdHolder;

    public MyRelatedAnswerdRecyclerAdapter(Context context, List<TwoBeen> beenList) {
        this.context = context;
        this.beenList = beenList;
    }

    @Override
    public int getItemViewType(int position) {
        int age = beenList.get(position).getAge();
        if (age == 1){
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = View.inflate(context, R.layout.relatedanswerd_recycler_item,null);
            relatedanswerdHolder = new RelatedAnswerdRecyclerHolder(view);
        }else {
            View view = View.inflate(context,R.layout.relatedanswerd_recycler_item2,null);
            relatedanswerdHolder = new RelatedAnswerdRecyclerHolder(view);
        }

        return relatedanswerdHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        relatedanswerdHolder= (RelatedAnswerdRecyclerHolder) holder;
        relatedanswerdHolder.textView.setText(beenList.get(position).getName());
        relatedanswerdHolder.textView1.setText(beenList.get(position).getSex());
    }

    @Override
    public int getItemCount() {
        return beenList.size();
    }

    class RelatedAnswerdRecyclerHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView1;
        public RelatedAnswerdRecyclerHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.text);
            textView1=itemView.findViewById(R.id.text2);

        }
    }
}
