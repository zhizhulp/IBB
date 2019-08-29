package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/4/12.
 */

public class PictureBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : ["/image/53441708-779f-4343-b8b7-31ff9aeda8d0.jpg"]
     */

    private MetaBean meta;
    private List<String> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
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
