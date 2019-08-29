package com.example.ibb.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/23.
 */

public class GeeksBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : []
     */

    private MetaBean meta;
    private List<?> data = new ArrayList<>();

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
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
