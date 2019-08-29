package com.example.ibb.ui.ui.add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

public class ChooseShoppingActivity extends BaseActivity implements View.OnClickListener {

    private TextView chooseshopping_qx_tv;
    private TextView chooseshopping_search_tv;

    @Override
    protected void initview() {

        chooseshopping_qx_tv = (TextView)findViewById(R.id.chooseshopping_qx_tv);
        chooseshopping_search_tv = (TextView)findViewById(R.id.chooseshopping_search_tv);

        chooseshopping_qx_tv.setOnClickListener(this);
        chooseshopping_search_tv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_choose_shopping;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseshopping_qx_tv:
                finish();
                break;
            case R.id.chooseshopping_search_tv:

                startActivity(new Intent(MyApp.activity,SearchResultsActivity.class));
                break;
        }
    }
}
