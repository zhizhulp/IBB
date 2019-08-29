package com.example.ibb.ui.ui.add;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;


import com.alibaba.fastjson.JSONObject;
import com.example.ibb.R;
import com.example.ibb.adapter.MyPopularBeautyListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;
import com.example.ibb.entity.BeautyBean;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 热门美妆
 * A simple {@link Fragment} subclass.
 */
public class PopularBeautyFragment extends BaseFragment {


    private View view;
    private ListView popularbeauty_lv;
    private List<BeautyBean.DataBean> beautyBeenList = new ArrayList<>();
    private int page = 1;
    private MyPopularBeautyListAdapter listAdapter;

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_popular_beauty, null);
        popularbeauty_lv = (ListView)view.findViewById(R.id.popularbeauty_lv);
        listAdapter = new MyPopularBeautyListAdapter(MyApp.activity,beautyBeenList);
        popularbeauty_lv.setAdapter(listAdapter);

    }

    @Override
    protected void initdata() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token", getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        FormBody body = new FormBody.Builder()
                .add("type",2+"")
                .add("index",page + "")
                .build();
        final Request request = new Request.Builder().url(URLApi.hotstring).post(body).build();
        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                beautyBeenList.clear();
//                Response execute = call.execute();

                String str = response.body().string();

                Log.e("TAG","---当前美妆数据=="+str);


                Gson gson = new Gson();

                BeautyBean beautyBean = gson.fromJson(str,BeautyBean.class);

                Log.e("TAG","解析美妆数据=="+beautyBean.toString());

                if (beautyBean.getMeta().isSuccess()){
                    if (beautyBean.getData()!=null){
                        beautyBeenList.addAll((Collection<? extends BeautyBean.DataBean>) beautyBean.getData());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                }

            }
        });



        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }


}
