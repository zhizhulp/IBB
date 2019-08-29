package com.example.ibb.entity;

/**
 * Created by 张凯雅 on 2017/12/29.
 */

public class CodeBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"code":6773,"hasPassword":true}
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
         * code : 6773
         * hasPassword : true
         */

        private int code;
        private boolean hasPassword;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isHasPassword() {
            return hasPassword;
        }

        public void setHasPassword(boolean hasPassword) {
            this.hasPassword = hasPassword;
        }
    }
}
