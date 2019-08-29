package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/4/19.
 * 首页 轮播图
 */

public class BannerBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"id":1,"image":"http://oss.ibbtech.cn/banner/banner1.jpg","referId":null,"link":null,"startTime":1523959200000,"endTime":1555495200000,"type":100},{"id":2,"image":"http://oss.ibbtech.cn/banner/banner2.jpg","referId":null,"link":null,"startTime":1523959200000,"endTime":1555495200000,"type":100},{"id":3,"image":"http://oss.ibbtech.cn/banner/banner4.jpg","referId":null,"link":null,"startTime":1523959200000,"endTime":1555495200000,"type":100},{"id":4,"image":"http://oss.ibbtech.cn/banner/banner4.jpg","referId":null,"link":null,"startTime":1523959200000,"endTime":1555495200000,"type":100}]
     */

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
         * id : 1
         * image : http://oss.ibbtech.cn/banner/banner1.jpg
         * referId : null
         * link : null
         * startTime : 1523959200000
         * endTime : 1555495200000
         * type : 100
         */

        private int id;
        private String image;
        private Object referId;
        private Object link;
        private long startTime;
        private long endTime;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getReferId() {
            return referId;
        }

        public void setReferId(Object referId) {
            this.referId = referId;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
