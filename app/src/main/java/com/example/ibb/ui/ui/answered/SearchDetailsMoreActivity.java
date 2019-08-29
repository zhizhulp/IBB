package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyDetailsMoreListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.SearchBoxBean;

/**
 * 首页搜索相关商品（查看更多）
 */
public class SearchDetailsMoreActivity extends BaseActivity implements View.OnClickListener {

    private ImageView detailsMore_back_iv;
    private ListView detailsMore_listview;
    private int page = 1;
    private MyDetailsMoreListAdapter listAdapter;

    @Override
    protected void initview() {

        detailsMore_back_iv = (ImageView)findViewById(R.id.detailsMore_back_iv);
        detailsMore_back_iv.setOnClickListener(this);

        detailsMore_listview = (ListView)findViewById(R.id.detailsMore_listview);
        listAdapter = new MyDetailsMoreListAdapter(MyApp.activity, ((SearchBoxBean) getIntent().getSerializableExtra("goodsList")).getData());
        detailsMore_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchBoxBean.DataBean item = (SearchBoxBean.DataBean) listAdapter.getItem(position);
                GoodsDetActivity.startGoodsDet(SearchDetailsMoreActivity.this,item.getId());
            }
        });
        detailsMore_listview.setAdapter(listAdapter);
    }

    public static void startGoodsList(Context context, SearchBoxBean searchBoxBean){
        Intent intent=new Intent(context,SearchDetailsMoreActivity.class);
        intent.putExtra("goodsList",searchBoxBean);
        context.startActivity(intent);
    }

    @Override
    protected void initdata() {
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_search_details_more;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detailsMore_back_iv:
                finish();
                break;
        }
    }
}
