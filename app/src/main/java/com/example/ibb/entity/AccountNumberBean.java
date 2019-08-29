package com.example.ibb.entity;

/**
 * Created by 张凯雅 on 2018/2/2.
 */

public class AccountNumberBean {


    /**
     * meta : {"success":true,"message":"ok"}
     * data : {"user":{"id":33913654738946,"nickname":null,"portrait":null,"phoneNumber":"15201250413"},"token":"423b367a-f91e-46f9-b0e3-0bf972a1742f"}
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
         * user : {"id":33913654738946,"nickname":null,"portrait":null,"phoneNumber":"15201250413"}
         * token : 423b367a-f91e-46f9-b0e3-0bf972a1742f
         */

        private User userinfo;
        private String token;

        public User getUser() {
            return userinfo;
        }

        public void setUser(User userinfo) {
            this.userinfo = userinfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * id : 33913654738946
             * nickname : null
             * portrait : null
             * phoneNumber : 15201250413
             */

            private long id;
            private Object nickname;
            private Object portrait;
            private String phoneNumber;

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

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }
        }
    }
}
