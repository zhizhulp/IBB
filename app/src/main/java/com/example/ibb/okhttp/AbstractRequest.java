package com.example.ibb.okhttp;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by ASUS on 2018/5/11.
 */

public abstract class AbstractRequest<T> extends Request<HttpBaseEntity<T>> {
    private String TAG="AbstractRequest";
    public AbstractRequest(String url) {
        super(url);
    }

    public AbstractRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    protected abstract T getResult(String data);

    @Override
    public HttpBaseEntity<T> parseResponse(Headers headers, byte[] body) throws Exception {
        int responseCode = headers.getResponseCode(); // 响应码。
        if (responseCode >= 200 && responseCode < 400) {
            if (body == null || body.length == 0) {
                return new HttpBaseEntity<>(null, null,"body is null",responseCode);
            } else {
                String bodyString = StringRequest.parseResponseString(headers, body);
                Log.d(TAG, "parseResponse: "+bodyString);
                JSONObject jObj = JSON.parseObject(bodyString);
                JSONObject metaObj = jObj.getJSONObject("meta");
                HttpBaseEntity.MetaBean metaBean=JSON.parseObject(metaObj.toJSONString(), HttpBaseEntity.MetaBean.class);
                T result = getResult(jObj.getString("data"));
                return new HttpBaseEntity<>(metaBean,result,null,responseCode);
            }
        } else if(responseCode==401){
            String error = "需要重新登陆";
            return new HttpBaseEntity<>(null,null,error,responseCode);
        } else {
            String error = "code >=401";
            return new HttpBaseEntity<>(null,null,error,responseCode);
        }
    }
}
