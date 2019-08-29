package com.example.ibb.entity;

/**
 * Created by 执笔画商
 * on 2018/4/9.
 * at 北京
 */

public class UserMessage {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"id":34252792987649,"nickname":"iBB-0319","portrait":"http://image.ibbtech.cn/image/portrait_default.png","signature":null,"location":null,"sex":0,"follow":0,"follower":0,"likes":0,"relation":4}
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
         * id : 34252792987649
         * nickname : iBB-0319
         * portrait : http://image.ibbtech.cn/image/portrait_default.png
         * signature : null
         * location : null
         * sex : 0
         * follow : 0
         * follower : 0
         * likes : 0
         * relation : 4
         */

        private long id;
        private String nickname;
        private String portrait;
        private String signature;
        private String location;
        private int sex;
        private int follow;
        private int follower;
        private int likes;
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

        public Object getSignature() {
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

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getFollower() {
            return follower;
        }

        public void setFollower(int follower) {
            this.follower = follower;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }
    }
}
