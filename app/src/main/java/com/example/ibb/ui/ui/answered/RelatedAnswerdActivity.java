package com.example.ibb.ui.ui.answered;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ibb.R;
import com.example.ibb.adapter.MyRelatedAnswerdRecyclerAdapter;
import com.example.ibb.adapter.TwoBeen;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RelatedAnswerdActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView relatedanswerd_recycler;
    private List<TwoBeen> beenList = new ArrayList<>();
    private RecyclerAdapterWithHF hf;
    private ImageView related_back_iv;

    @Override
    protected void initview() {

        relatedanswerd_recycler = (RecyclerView)findViewById(R.id.relatedanswerd_recycler);
        relatedanswerd_recycler.setLayoutManager(new LinearLayoutManager(MyApp.activity,LinearLayoutManager.VERTICAL,false));
        MyRelatedAnswerdRecyclerAdapter recyclerAdapter = new MyRelatedAnswerdRecyclerAdapter(MyApp.activity,beenList);
        hf = new RecyclerAdapterWithHF(recyclerAdapter);
        relatedanswerd_recycler.setAdapter(hf);

        related_back_iv =(ImageView)findViewById(R.id.related_back_iv);
        related_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        beenList.add(new TwoBeen("Dyson的吹风机真的值得买么？为什么会这样备受追捧？",1,"真水五香：想知道这个吹风机功率大么手持会觉得重么？为什么戴森吹风机的价格如此高？"));
        beenList.add(new TwoBeen("买对了YSL，但是我为什么还是跪在了搓衣板上:)",1,"芦苇微微：哇……这个真是是难受辛辛苦苦通过各种关系帮他买到买到买到……"));
        beenList.add(new TwoBeen("Dyson的吹风机真的值得买么？为什么会这样备受追捧？",2,"真水五香：想知道这个吹风机功率大么手持会觉得重么？为什么戴森吹风机的价格如此高？"));
        beenList.add(new TwoBeen("买对了YSL，但是我为什么还是跪在了搓衣板上:)",2,"芦苇微微：哇……这个真是是难受辛辛苦苦通过各种关系帮他买到买到买到……"));

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_related_answerd;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.related_back_iv:
                finish();
                break;
        }
    }
}
