package com.example.ibb.ui.ui.add;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.ibb.R;
import com.example.ibb.adapter.MyPopulargeeksListAdapter;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;
import com.example.ibb.custom_view.MyListView;
import com.example.ibb.entity.GeeksBean;
import com.example.ibb.apimanager.URLApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 热门极客
 * A simple {@link Fragment} subclass.
 */
public class PopularGeeksFragment extends BaseFragment {

    private View view;
    private List<GeeksBean> geeksBeanList = new ArrayList<>();
    private ListView populargeeks_lv;
    private int page = 1;
    private MyPopulargeeksListAdapter geeksListAdapter;

    @Override
    protected void initview() {
        view = LayoutInflater.from(MyApp.activity).inflate(R.layout.fragment_popular_geeks, null);
        populargeeks_lv = (ListView)view.findViewById(R.id.populargeeks_lv);
        geeksListAdapter = new MyPopulargeeksListAdapter(MyApp.activity,geeksBeanList);
        populargeeks_lv.setAdapter(geeksListAdapter);
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
                .add("type",1+"")
                .add("index",page + "")
                .add("pageSize",10 + "")
                .build();
        Request request = new Request.Builder().url(URLApi.hotstring).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("TAG","------极客"+string);
                if (getActivity() != null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            geeksBeanList.clear();
                            Gson gson = new Gson();
                            GeeksBean geeksBean = gson.fromJson(string, GeeksBean.class);
//                        geeksListAdapter.notifyDataSetChanged();

                        }
                    });
                }

            }
        });
    }

    @Override
    protected View initlayout() {
        return view;
    }

    @Override
    protected void restartdata() {

    }

}
