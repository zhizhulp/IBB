package com.example.ibb.ui.ui.mine;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.ImageView_circle;

/**
 * 添加
 */

public class PeopleActivity extends BaseActivity implements View.OnClickListener {


    private ImageView people_userpicture_iv;
    private ImageView people_addto;
    private RadioButton people_answered_radiobt;
    private RadioButton people_questions_radiobt;
    private ImageView people_back;
    private PeopleAnsweredFragment peopleansweredFragment;
    private PeopleQuestionsFragment peoplequestionsFragment;

    @Override
    protected void initview() {
        people_userpicture_iv =(ImageView)findViewById(R.id.people_userpicture_iv);
        //圆形图片
        Glide.with(this).load(R.mipmap.b3).transform(new ImageView_circle(this)).into(people_userpicture_iv);
        people_addto =(ImageView)findViewById(R.id.people_addto);
        //圆形图片
        Glide.with(this).load(R.mipmap.addto).transform(new ImageView_circle(this)).into(people_addto);

        people_back =(ImageView)findViewById(R.id.people_back);
        people_answered_radiobt =(RadioButton)findViewById(R.id.people_answered_radiobt);
        people_questions_radiobt =(RadioButton)findViewById(R.id.people_questions_radiobt);

        people_back.setOnClickListener(this);
        people_answered_radiobt.setOnClickListener(this);
        people_questions_radiobt.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

        peopleansweredFragment = new PeopleAnsweredFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.people_fm,peopleansweredFragment);
        transaction.show(peopleansweredFragment);
        transaction.commit();

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_people;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        initHide(transaction);
        switch (v.getId()){
            case R.id.people_back:
                finish();
                break;

            case R.id.people_answered_radiobt:
                if (peopleansweredFragment == null){
                    peopleansweredFragment = new PeopleAnsweredFragment();
                    transaction.add(R.id.people_fm, peopleansweredFragment);
                }else {
                    transaction.show(peopleansweredFragment);
                }
                break;

            case R.id.people_questions_radiobt:
                if (peoplequestionsFragment == null){
                    peoplequestionsFragment = new PeopleQuestionsFragment();
                    transaction.add(R.id.people_fm, peoplequestionsFragment);
                }else{
                    transaction.show(peoplequestionsFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void initHide(FragmentTransaction transaction) {
        if (peopleansweredFragment != null){
            transaction.hide(peopleansweredFragment);
        }
        if (peoplequestionsFragment != null){
            transaction.hide(peoplequestionsFragment);
        }
    }
}
