package com.example.ibb.okhttp;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.RequestMethod;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/11.
 */

public class EntityListRequest<T> extends AbstractRequest<List<T>> {

    private Class<T> clazz;

    public EntityListRequest(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        this.clazz = clazz;
    }

    @Override
    protected List<T> getResult(String data) {
        return JSON.parseArray(data,clazz);
    }
}
