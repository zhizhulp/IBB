package com.example.ibb.okhttp;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.RequestMethod;

/**
 * Created by ASUS on 2018/5/11.
 */

public class EntityRequest<T> extends AbstractRequest<T> {
    private Class<T> clazz;

    public EntityRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        this.clazz = clazz;
    }

    @Override
    protected T getResult(String data) {
        return new Gson().fromJson(data,clazz);
    }
}
