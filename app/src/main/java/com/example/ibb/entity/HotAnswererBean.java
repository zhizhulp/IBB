package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/4/10.
 */

public class HotAnswererBean {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"id":34183373848578,"nickname":"测试人员111","portrait":"http://image.ibbtech.cn/image/8b648aa9-557c-4c23-811f-016c95cfe8de.jpg","signature":"签名不知道怎么填","location":"北京市 北京市 房山区","sex":1,"relation":0},{"id":34185069133827,"nickname":"我","portrait":"http://oss.ibbtech.cn/headimage.png","signature":null,"location":null,"sex":0,"relation":0},{"id":34259514359811,"nickname":"iBB-5326","portrait":"http://image.ibbtech.cn/image/portrait_default.png","signature":null,"location":null,"sex":0,"relation":0},{"id":34179807641601,"nickname":"disypen","portrait":"http://oss.ibbtech.cn/headimage.png","signature":"even you ","location":null,"sex":0,"relation":0}]
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
         * id : 34183373848578
         * nickname : 测试人员111
         * portrait : http://image.ibbtech.cn/image/8b648aa9-557c-4c23-811f-016c95cfe8de.jpg
         * signature : 签名不知道怎么填
         * location : 北京市 北京市 房山区
         * sex : 1
         * relation : 0
         */

        private long id;
        private String nickname;
        private String portrait;
        private String signature;
        private String location;
        private int sex;
        private int relation;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }
    }
}
