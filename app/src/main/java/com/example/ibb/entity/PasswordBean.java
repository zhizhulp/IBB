package com.example.ibb.entity;

/**
 * Created by 张凯雅 on 2018/1/10.
 */

public class PasswordBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : 设置密码成功
     */

    private MetaBean meta;
    private String data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
    }
}
