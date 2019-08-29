package com.example.ibb.entity.base_entity;

/**
 * Created by ASUS on 2018/5/11.
 */

public class HttpBaseEntity<T> {
    public HttpBaseEntity(MetaBean meta, T data, String error, int code) {
        this.meta = meta;
        this.data = data;
        this.error = error;
        this.code = code;
    }

    private MetaBean meta;
    private T data;
    private String error;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "MetaBean{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HttpBaseEntity{" +
                "meta=" + meta +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
