package com.example.ibb.ui.ui.answered.details;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ibb.R;
import com.example.ibb.adapter.MyDetailsRecyclerAdapter;
import com.example.ibb.adapter.TwoBeen;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.ui.ui.answered.MineAnsweredActivity;
import com.example.ibb.ui.ui.answered.AnswerDetActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {


    private LinearLayout ll_bottom;
    private DetailsScrollView mScrollView;
    private Animation showAnim;
    private Animation dismissAnim;
    private CustomToolbar TopcustomToolbarl;
    private CustomToolbar DowncustomToolbarl;
    private RecyclerView details_recyclerView;
    private List<TwoBeen> list = new ArrayList<>();
    private MyDetailsRecyclerAdapter adapter;
    private RecyclerAdapterWithHF hf;
    private TextView lt_main_title;
    private GoTopScrollview myscrollview;
    private TextView lt_main_title_yc;
    private ImageView details_back_iv;
    private Button answerd_bt;


    @Override
    protected void initview() {
        details_back_iv = (ImageView)findViewById(R.id.details_back_iv);
        myscrollview = (GoTopScrollview)findViewById(R.id.myscrollview);
//        lt_main_title =(TextView)findViewById(R.id.lt_main_title);
//        lt_main_title_yc = (TextView)findViewById(R.id.lt_main_title_yc);
        details_recyclerView =(RecyclerView)findViewById(R.id.details_recyclerView);
        answerd_bt = (Button)findViewById(R.id.answerd_bt);
        details_recyclerView.setNestedScrollingEnabled(false);
        details_recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.activity,LinearLayoutManager.VERTICAL,false));
        adapter = new MyDetailsRecyclerAdapter(MyApp.activity,list);
        hf = new RecyclerAdapterWithHF(adapter);
        details_recyclerView.setAdapter(hf);

        details_back_iv.setOnClickListener(this);
        answerd_bt.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

//        lt_main_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MyApp.activity,TiDaoProductActivity.class));
//            }
//        });
//        myscrollview.setOnClickListener(lt_main_title, lt_main_title_yc);
//        myscrollview.setHeight(50);

        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Intent intent = new Intent(MyApp.activity, AnswerDetActivity.class);
                startActivity(intent);
            }
        });

        list.add(new TwoBeen("来自杭州的Sam老师",1,"真水五香：想知道这个吹风机功率大么手持会觉得重么？为什么戴森吹风机的价格如此高？"));
        list.add(new TwoBeen("月中小胖",2,"芦苇微微：哇……这个真是是难受辛辛苦苦通过各种关系帮他买到买到买到……"));
        list.add(new TwoBeen("来自杭州的Sam老师",1,"真水五香：想知道这个吹风机功率大么手持会觉得重么？为什么戴森吹风机的价格如此高？"));
        list.add(new TwoBeen("月中小胖",2,"芦苇微微：哇……这个真是是难受辛辛苦苦通过各种关系帮他买到买到买到……"));
        list.add(new TwoBeen("来自杭州的Sam老师",1,"真水五香：想知道这个吹风机功率大么手持会觉得重么？为什么戴森吹风机的价格如此高？"));
        list.add(new TwoBeen("月中小胖",2,"芦苇微微：哇……这个真是是难受辛辛苦苦通过各种关系帮他买到买到买到……"));

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_details;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_back_iv:
                finish();
                break;
            case R.id.answerd_bt:
                startActivity(new Intent(MyApp.activity,MineAnsweredActivity.class));
                break;
        }
    }
}
