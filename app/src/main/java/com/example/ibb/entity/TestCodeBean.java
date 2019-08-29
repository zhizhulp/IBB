package com.example.ibb.entity;

/**
 * Created by 张凯雅 on 2018/2/6.
 */

public class TestCodeBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"token":"1b7fb01f-8ec2-4f09-a723-27e11b90f9ff"}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * token : 1b7fb01f-8ec2-4f09-a723-27e11b90f9ff
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
