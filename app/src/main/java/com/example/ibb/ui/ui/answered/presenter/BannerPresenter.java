package com.example.ibb.ui.ui.answered.presenter;

import com.example.ibb.ui.ui.answered.Bean;
import com.example.ibb.ui.ui.answered.MyView;
import com.example.ibb.ui.ui.answered.model.OkHttpView;
import com.example.ibb.ui.ui.answered.model.OkHttplistener;
import com.example.ibb.ui.ui.answered.model.OkHttpmodel;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class BannerPresenter implements BannerView ,OkHttplistener{

        private OkHttpView okHttpView;
    private MyView myView;

    public BannerPresenter(MyView myView) {
        this.myView=myView;
        okHttpView = new OkHttpmodel();
    }
    @Override
    public void bannse(String url) {
        okHttpView.Banners(url,this);

    }

    @Override
    public void OnSuccess(Bean bean) {
        myView.bann(bean);
    }
}
