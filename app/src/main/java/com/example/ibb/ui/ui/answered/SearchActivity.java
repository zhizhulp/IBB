package com.example.ibb.ui.ui.answered;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.HotBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends BaseNetActivity implements View.OnClickListener {

    private TextView search_tv;
    private TagGroup search_dynamic_tag;
    private List<String> tags = new ArrayList<String>();
    private ImageView search_back_iv;
    private ImageView search_empty_iv;
    private EditText search_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initview();
        requestTags();
    }

    private void requestTags() {
        Request request = buildPostListRequest(URLApi.hotSearch, HotBean.class);
        doRequest(0, request);
    }

    protected void initview() {
        search_tv = (TextView) findViewById(R.id.search_tv);
        search_tv.setOnClickListener(this);
        //搜索框
        search_editText = (EditText) findViewById(R.id.search_editText);
        //X号
        search_empty_iv = (ImageView) findViewById(R.id.search_empty_iv);
        search_empty_iv.setOnClickListener(this);

        search_dynamic_tag = (TagGroup) findViewById(R.id.search_dynamic_tag);
        search_dynamic_tag.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                SearchDetailsActivity.startSearchDet(SearchActivity.this, tag);
            }
        });
        search_back_iv = (ImageView) findViewById(R.id.search_back_iv);
        search_back_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back_iv:
                finish();
                break;
            case R.id.search_tv:
                if (search_editText.getText().length() == 0) {
                    ToastUtils.show("请输入关键字");
                } else {
                    SearchDetailsActivity.startSearchDet(this, search_editText.getText().toString());
                }
                break;
            case R.id.search_empty_iv://x号
                search_editText.setText("");
                break;
        }
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        List<HotBean> hotBeans = (List<HotBean>) data.getData();
        for (int i = 0; i < hotBeans.size(); i++) {
            tags.add(hotBeans.get(i).getWord());
        }
        search_dynamic_tag.setTags(tags);
    }
}
