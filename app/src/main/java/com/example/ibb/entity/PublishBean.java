package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/21.
 */

public class PublishBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"id":152162689683068,"state":0,"time":"2018-03-21 18:08:16","title":"测试","replyCount":0,"likeCount":0,"followCount":0,"viewCount":0,"shareCount":0,"mentionProducts":[],"categorys":[],"user":{"id":34185069133827,"nickname":null,"portrait":null,"signature":null,"location":null,"sex":0,"relation":0},"follow":false,"share":false}
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
         * id : 152162689683068
         * state : 0
         * time : 2018-03-21 18:08:16
         * title : 测试
         * replyCount : 0
         * likeCount : 0
         * followCount : 0
         * viewCount : 0
         * shareCount : 0
         * mentionProducts : []
         * categorys : []
         * user : {"id":34185069133827,"nickname":null,"portrait":null,"signature":null,"location":null,"sex":0,"relation":0}
         * follow : false
         * share : false
         */

        private long id;
        private int state;
        private String time;
        private String title;
        private int replyCount;
        private int likeCount;
        private int followCount;
        private int viewCount;
        private int shareCount;
        private UserBean user;
        private boolean follow;
        private boolean share;
        private List<?> mentionProducts;
        private List<?> categorys;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public boolean isShare() {
            return share;
        }

        public void setShare(boolean share) {
            this.share = share;
        }

        public List<?> getMentionProducts() {
            return mentionProducts;
        }

        public void setMentionProducts(List<?> mentionProducts) {
            this.mentionProducts = mentionProducts;
        }

        public List<?> getCategorys() {
            return categorys;
        }

        public void setCategorys(List<?> categorys) {
            this.categorys = categorys;
        }

        public static class UserBean {
            /**
             * id : 34185069133827
             * nickname : null
             * portrait : null
             * signature : null
             * location : null
             * sex : 0
             * relation : 0
             */

            private long id;
            private Object nickname;
            private Object portrait;
            private Object signature;
            private Object location;
            private int sex;
            private int relation;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getPortrait() {
                return portrait;
            }

            public void setPortrait(Object portrait) {
                this.portrait = portrait;
            }

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
                this.signature = signature;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
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
}
