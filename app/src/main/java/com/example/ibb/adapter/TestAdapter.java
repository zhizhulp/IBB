package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.utils.ScreenDpiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */

public class TestAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        List<Object> data2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data2.add(new Object());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        Radapter radapter = new Radapter(R.layout.find_recycler_item, data2);
        recyclerView.setAdapter(radapter);
    }

    public static class Radapter extends BaseQuickAdapter<Object, BaseViewHolder> {

        public Radapter(int layoutResId, @Nullable List<Object> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
            int leftMargin = lp.leftMargin;
            int rightMargin = lp.rightMargin;
            int topMargin = lp.topMargin;
            int bottomMargin = lp.bottomMargin;
            if (helper.getAdapterPosition()==0){
                lp.setMargins((int) ScreenDpiUtils.dp2px(mContext,250), topMargin, rightMargin, bottomMargin);
            }else {
                lp.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            }
        }
    }
}
