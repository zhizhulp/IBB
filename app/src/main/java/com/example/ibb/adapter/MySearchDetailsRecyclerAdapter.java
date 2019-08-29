package com.example.ibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.entity.QABean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/9.
 */

public class MySearchDetailsRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<QABean.DataBean> qaDataBeanList;
    private SearchDetailsRecyclerHolder searchHolder;


    public MySearchDetailsRecyclerAdapter(Context context, List<QABean.DataBean> qaDataBeanList) {
        this.context = context;
        this.qaDataBeanList = qaDataBeanList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_recycler_item2, null);
        searchHolder = new SearchDetailsRecyclerHolder(view);
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        searchHolder = (SearchDetailsRecyclerHolder) holder;
        searchHolder.textView.setText(qaDataBeanList.get(position).getTitle());
        String content = qaDataBeanList.get(position).getContent();
        if (TextUtils.isEmpty(content)) {
            searchHolder.latContent.setVisibility(View.GONE);
        } else {
            searchHolder.latContent.setVisibility(View.VISIBLE);
            searchHolder.textView1.setText(content);
        }
        searchHolder.likeCt.setText(qaDataBeanList.get(position).getLikeCount()+"赞");
        searchHolder.commentCt.setText(qaDataBeanList.get(position).getLikeCount()+"评");
    }

    @Override
    public int getItemCount() {
        return qaDataBeanList.size();
    }

    class SearchDetailsRecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        TextView likeCt;
        TextView commentCt;
        ImageView search_item_image;
        View latContent;

        public SearchDetailsRecyclerHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            textView1 = itemView.findViewById(R.id.text2);
            search_item_image = itemView.findViewById(R.id.search_item_image);
            latContent = itemView.findViewById(R.id.lat_content);
            likeCt = itemView.findViewById(R.id.tv_like_count);

            commentCt = itemView.findViewById(R.id.tv_comment_count);

        }
    }
}
