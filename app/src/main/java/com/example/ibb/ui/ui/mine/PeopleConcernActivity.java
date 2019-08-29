package com.example.ibb.ui.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.ImageView_circle;

public class PeopleConcernActivity extends BaseActivity implements View.OnClickListener {


    private ImageView people_concern__back;
    private ImageView people_concern_userpicture_iv;

    @Override
    protected void initview() {
        people_concern__back =(ImageView)findViewById(R.id.people_concern__back);
        people_concern_userpicture_iv =(ImageView)findViewById(R.id.people_concern_userpicture_iv);
        //圆形图片
        Glide.with(this).load(R.mipmap.b3).transform(new ImageView_circle(this)).into(people_concern_userpicture_iv);

        people_concern__back.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_people_concern;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.people_concern__back:
                finish();
                break;

        }
    }
}
