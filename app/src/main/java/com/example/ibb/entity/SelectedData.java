package com.example.ibb.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ibb.utils.FinalUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/4/4.
 */

public class SelectedData {

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

    public static class DataBean implements MultiItemEntity {
        /**
         * id : 152240120549346
         * title : 测试加商品两个？
         * content : 点击输入正文
         * replyCount : 3
         * likeCount : 0
         * followCount : 0
         * answer : {"id":7,"content":"嗯嗯","questionId":152240120549346,"likeCount":0,"replyCount":0,"favoriteCount":0,"shareCount":0,"viewCount":0,"time":"2018-04-04 10:55:49","like":false,"favorite":false,"user":{"id":34183373848578,"nickname":"爱哔哔测试1","portrait":"http://image.ibbtech.cn/image/3db1be6a-38c9-4c07-8185-335b8a8757bc.jpg","signature":null,"location":"北京市 北京市 丰台区","sex":1,"relation":0},"myProducts":[],"questionProducts":[{"id":119,"questionId":152240120549346,"answerId":null,"productId":1,"score":0,"product":{"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]},"attrs":[{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]},{"id":120,"questionId":152240120549346,"answerId":null,"productId":2,"score":0,"product":{"id":2,"name":"Yumeijing 郁美净 儿童面霜","minPrice":15,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":1,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/22.html","type":0,"favorite":false,"category":{"id":1059,"name":"面霜","icon":null,"sequence":7,"parentId":109,"children":null},"brand":{"id":1683,"nameCn":"郁美净","nameEn":"Yumeijing","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/郁美净儿童霜.jpg"],"infos":[{"id":2,"name":"儿童安全","productId":2}],"baseAttribute":[{"id":10,"name":"肌肤保湿","score":0},{"id":29,"name":"延展性","score":0},{"id":16,"name":"美白度","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":18,"name":"抗衰老","score":0},{"id":6,"name":"性价比","score":0},{"id":155,"name":"测试","score":0}]},"attrs":[{"id":102,"questionProductId":120,"attributeId":10,"score":0,"attribute":{"id":10,"name":"肌肤保湿"}},{"id":103,"questionProductId":120,"attributeId":29,"score":0,"attribute":{"id":29,"name":"延展性"}},{"id":104,"questionProductId":120,"attributeId":16,"score":0,"attribute":{"id":16,"name":"美白度"}},{"id":105,"questionProductId":120,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":106,"questionProductId":120,"attributeId":18,"score":0,"attribute":{"id":18,"name":"抗衰老"}},{"id":107,"questionProductId":120,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":108,"questionProductId":120,"attributeId":155,"score":0,"attribute":{"id":155,"name":"测试"}}]}],"relateQuestions":[]}
         * user : {"id":34183373848578,"nickname":"爱哔哔测试1","portrait":"http://image.ibbtech.cn/image/3db1be6a-38c9-4c07-8185-335b8a8757bc.jpg","signature":null,"location":"北京市 北京市 丰台区","sex":1,"relation":0}
         * categorys : []
         * mentionProducts : [{"id":119,"questionId":152240120549346,"answerId":null,"productId":1,"score":0,"product":{"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]},"attrs":[{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]},{"id":120,"questionId":152240120549346,"answerId":null,"productId":2,"score":0,"product":{"id":2,"name":"Yumeijing 郁美净 儿童面霜","minPrice":15,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":1,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/22.html","type":0,"favorite":false,"category":{"id":1059,"name":"面霜","icon":null,"sequence":7,"parentId":109,"children":null},"brand":{"id":1683,"nameCn":"郁美净","nameEn":"Yumeijing","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/郁美净儿童霜.jpg"],"infos":[{"id":2,"name":"儿童安全","productId":2}],"baseAttribute":[{"id":10,"name":"肌肤保湿","score":0},{"id":29,"name":"延展性","score":0},{"id":16,"name":"美白度","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":18,"name":"抗衰老","score":0},{"id":6,"name":"性价比","score":0},{"id":155,"name":"测试","score":0}]},"attrs":[{"id":102,"questionProductId":120,"attributeId":10,"score":0,"attribute":{"id":10,"name":"肌肤保湿"}},{"id":103,"questionProductId":120,"attributeId":29,"score":0,"attribute":{"id":29,"name":"延展性"}},{"id":104,"questionProductId":120,"attributeId":16,"score":0,"attribute":{"id":16,"name":"美白度"}},{"id":105,"questionProductId":120,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":106,"questionProductId":120,"attributeId":18,"score":0,"attribute":{"id":18,"name":"抗衰老"}},{"id":107,"questionProductId":120,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":108,"questionProductId":120,"attributeId":155,"score":0,"attribute":{"id":155,"name":"测试"}}]}]
         * products : [{"id":119,"questionId":152240120549346,"answerId":null,"productId":1,"score":0,"product":{"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]},"attrs":[{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]},{"id":120,"questionId":152240120549346,"answerId":null,"productId":2,"score":0,"product":{"id":2,"name":"Yumeijing 郁美净 儿童面霜","minPrice":15,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":1,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/22.html","type":0,"favorite":false,"category":{"id":1059,"name":"面霜","icon":null,"sequence":7,"parentId":109,"children":null},"brand":{"id":1683,"nameCn":"郁美净","nameEn":"Yumeijing","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/郁美净儿童霜.jpg"],"infos":[{"id":2,"name":"儿童安全","productId":2}],"baseAttribute":[{"id":10,"name":"肌肤保湿","score":0},{"id":29,"name":"延展性","score":0},{"id":16,"name":"美白度","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":18,"name":"抗衰老","score":0},{"id":6,"name":"性价比","score":0},{"id":155,"name":"测试","score":0}]},"attrs":[{"id":102,"questionProductId":120,"attributeId":10,"score":0,"attribute":{"id":10,"name":"肌肤保湿"}},{"id":103,"questionProductId":120,"attributeId":29,"score":0,"attribute":{"id":29,"name":"延展性"}},{"id":104,"questionProductId":120,"attributeId":16,"score":0,"attribute":{"id":16,"name":"美白度"}},{"id":105,"questionProductId":120,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":106,"questionProductId":120,"attributeId":18,"score":0,"attribute":{"id":18,"name":"抗衰老"}},{"id":107,"questionProductId":120,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":108,"questionProductId":120,"attributeId":155,"score":0,"attribute":{"id":155,"name":"测试"}}]}]
         * time : 2018-03-30 17:13:25
         */

        private long id;
        private String title;
        private String content;
        private int replyCount;
        private int likeCount;
        private int followCount;
        private AnswerBean answer;
        private UserBeanX user;
        private String time;
        private List<?> categorys;
        private List<MentionProductsBean> mentionProducts;
        private List<ProductsBean> products;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public AnswerBean getAnswer() {
            return answer;
        }

        public void setAnswer(AnswerBean answer) {
            this.answer = answer;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<?> getCategorys() {
            return categorys;
        }

        public void setCategorys(List<?> categorys) {
            this.categorys = categorys;
        }

        public List<MentionProductsBean> getMentionProducts() {
            return mentionProducts;
        }

        public void setMentionProducts(List<MentionProductsBean> mentionProducts) {
            this.mentionProducts = mentionProducts;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        @Override
        public int getItemType() {
            List<MentionProductsBean> mentionProducts = getMentionProducts();
            String content = getContent();
            if (mentionProducts.size() > 0) {
                return 1;
            } else if (null != content && content.contains(FinalUtils.IMGS_BASE)) {
                return 2;
            } else {
                return 3;
            }
        }

        public static class AnswerBean {
            /**
             * id : 7
             * content : 嗯嗯
             * questionId : 152240120549346
             * likeCount : 0
             * replyCount : 0
             * favoriteCount : 0
             * shareCount : 0
             * viewCount : 0
             * time : 2018-04-04 10:55:49
             * like : false
             * favorite : false
             * user : {"id":34183373848578,"nickname":"爱哔哔测试1","portrait":"http://image.ibbtech.cn/image/3db1be6a-38c9-4c07-8185-335b8a8757bc.jpg","signature":null,"location":"北京市 北京市 丰台区","sex":1,"relation":0}
             * myProducts : []
             * questionProducts : [{"id":119,"questionId":152240120549346,"answerId":null,"productId":1,"score":0,"product":{"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]},"attrs":[{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]},{"id":120,"questionId":152240120549346,"answerId":null,"productId":2,"score":0,"product":{"id":2,"name":"Yumeijing 郁美净 儿童面霜","minPrice":15,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":1,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/22.html","type":0,"favorite":false,"category":{"id":1059,"name":"面霜","icon":null,"sequence":7,"parentId":109,"children":null},"brand":{"id":1683,"nameCn":"郁美净","nameEn":"Yumeijing","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/郁美净儿童霜.jpg"],"infos":[{"id":2,"name":"儿童安全","productId":2}],"baseAttribute":[{"id":10,"name":"肌肤保湿","score":0},{"id":29,"name":"延展性","score":0},{"id":16,"name":"美白度","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":18,"name":"抗衰老","score":0},{"id":6,"name":"性价比","score":0},{"id":155,"name":"测试","score":0}]},"attrs":[{"id":102,"questionProductId":120,"attributeId":10,"score":0,"attribute":{"id":10,"name":"肌肤保湿"}},{"id":103,"questionProductId":120,"attributeId":29,"score":0,"attribute":{"id":29,"name":"延展性"}},{"id":104,"questionProductId":120,"attributeId":16,"score":0,"attribute":{"id":16,"name":"美白度"}},{"id":105,"questionProductId":120,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":106,"questionProductId":120,"attributeId":18,"score":0,"attribute":{"id":18,"name":"抗衰老"}},{"id":107,"questionProductId":120,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":108,"questionProductId":120,"attributeId":155,"score":0,"attribute":{"id":155,"name":"测试"}}]}]
             * relateQuestions : []
             */

            private int id;
            private String content;
            private long questionId;
            private int likeCount;
            private int replyCount;
            private int favoriteCount;
            private int shareCount;
            private int viewCount;
            private String time;
            private boolean like;
            private boolean favorite;
            private UserBean user;
            private List<?> myProducts;
            private List<QuestionProductsBean> questionProducts;
            private List<?> relateQuestions;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getQuestionId() {
                return questionId;
            }

            public void setQuestionId(long questionId) {
                this.questionId = questionId;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public int getFavoriteCount() {
                return favoriteCount;
            }

            public void setFavoriteCount(int favoriteCount) {
                this.favoriteCount = favoriteCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public boolean isLike() {
                return like;
            }

            public void setLike(boolean like) {
                this.like = like;
            }

            public boolean isFavorite() {
                return favorite;
            }

            public void setFavorite(boolean favorite) {
                this.favorite = favorite;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<?> getMyProducts() {
                return myProducts;
            }

            public void setMyProducts(List<?> myProducts) {
                this.myProducts = myProducts;
            }

            public List<QuestionProductsBean> getQuestionProducts() {
                return questionProducts;
            }

            public void setQuestionProducts(List<QuestionProductsBean> questionProducts) {
                this.questionProducts = questionProducts;
            }

            public List<?> getRelateQuestions() {
                return relateQuestions;
            }

            public void setRelateQuestions(List<?> relateQuestions) {
                this.relateQuestions = relateQuestions;
            }

            public static class UserBean {
                /**
                 * id : 34183373848578
                 * nickname : 爱哔哔测试1
                 * portrait : http://image.ibbtech.cn/image/3db1be6a-38c9-4c07-8185-335b8a8757bc.jpg
                 * signature : null
                 * location : 北京市 北京市 丰台区
                 * sex : 1
                 * relation : 0
                 */

                private long id;
                private String nickname;
                private String portrait;
                private Object signature;
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

                public Object getSignature() {
                    return signature;
                }

                public void setSignature(Object signature) {
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

            public static class QuestionProductsBean {
                /**
                 * id : 119
                 * questionId : 152240120549346
                 * answerId : null
                 * productId : 1
                 * score : 0.0
                 * product : {"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]}
                 * attrs : [{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]
                 */

                private int id;
                private long questionId;
                private Object answerId;
                private int productId;
                private double score;
                private ProductBean product;
                private List<AttrsBean> attrs;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(long questionId) {
                    this.questionId = questionId;
                }

                public Object getAnswerId() {
                    return answerId;
                }

                public void setAnswerId(Object answerId) {
                    this.answerId = answerId;
                }

                public int getProductId() {
                    return productId;
                }

                public void setProductId(int productId) {
                    this.productId = productId;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public ProductBean getProduct() {
                    return product;
                }

                public void setProduct(ProductBean product) {
                    this.product = product;
                }

                public List<AttrsBean> getAttrs() {
                    return attrs;
                }

                public void setAttrs(List<AttrsBean> attrs) {
                    this.attrs = attrs;
                }

                public static class ProductBean {
                    /**
                     * id : 1
                     * name : Hiruscar 喜辽复 修护凝胶
                     * minPrice : 139.0
                     * maxPrice : 175.0
                     * score : 0.0
                     * favoriteCount : 0
                     * evaluateCount : 0
                     * viewCount : 3
                     * shareCount : 2
                     * infoUrl : http://www.ibbtech.cn/product/21.html
                     * type : 0
                     * favorite : false
                     * category : {"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null}
                     * brand : {"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2}
                     * imgs : ["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"]
                     * infos : [{"id":1,"name":"肤质通用","productId":1}]
                     * baseAttribute : [{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]
                     */

                    private int id;
                    private String name;
                    private double minPrice;
                    private double maxPrice;
                    private double score;
                    private int favoriteCount;
                    private int evaluateCount;
                    private int viewCount;
                    private int shareCount;
                    private String infoUrl;
                    private int type;
                    private boolean favorite;
                    private CategoryBean category;
                    private BrandBean brand;
                    private List<String> imgs;
                    private List<InfosBean> infos;
                    private List<BaseAttributeBean> baseAttribute;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public double getMinPrice() {
                        return minPrice;
                    }

                    public void setMinPrice(double minPrice) {
                        this.minPrice = minPrice;
                    }

                    public double getMaxPrice() {
                        return maxPrice;
                    }

                    public void setMaxPrice(double maxPrice) {
                        this.maxPrice = maxPrice;
                    }

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }

                    public int getFavoriteCount() {
                        return favoriteCount;
                    }

                    public void setFavoriteCount(int favoriteCount) {
                        this.favoriteCount = favoriteCount;
                    }

                    public int getEvaluateCount() {
                        return evaluateCount;
                    }

                    public void setEvaluateCount(int evaluateCount) {
                        this.evaluateCount = evaluateCount;
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

                    public String getInfoUrl() {
                        return infoUrl;
                    }

                    public void setInfoUrl(String infoUrl) {
                        this.infoUrl = infoUrl;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public boolean isFavorite() {
                        return favorite;
                    }

                    public void setFavorite(boolean favorite) {
                        this.favorite = favorite;
                    }

                    public CategoryBean getCategory() {
                        return category;
                    }

                    public void setCategory(CategoryBean category) {
                        this.category = category;
                    }

                    public BrandBean getBrand() {
                        return brand;
                    }

                    public void setBrand(BrandBean brand) {
                        this.brand = brand;
                    }

                    public List<String> getImgs() {
                        return imgs;
                    }

                    public void setImgs(List<String> imgs) {
                        this.imgs = imgs;
                    }

                    public List<InfosBean> getInfos() {
                        return infos;
                    }

                    public void setInfos(List<InfosBean> infos) {
                        this.infos = infos;
                    }

                    public List<BaseAttributeBean> getBaseAttribute() {
                        return baseAttribute;
                    }

                    public void setBaseAttribute(List<BaseAttributeBean> baseAttribute) {
                        this.baseAttribute = baseAttribute;
                    }

                    public static class CategoryBean {
                        /**
                         * id : 1069
                         * name : 祛痘
                         * icon : null
                         * sequence : 17
                         * parentId : 109
                         * children : null
                         */

                        private int id;
                        private String name;
                        private Object icon;
                        private int sequence;
                        private int parentId;
                        private Object children;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public Object getIcon() {
                            return icon;
                        }

                        public void setIcon(Object icon) {
                            this.icon = icon;
                        }

                        public int getSequence() {
                            return sequence;
                        }

                        public void setSequence(int sequence) {
                            this.sequence = sequence;
                        }

                        public int getParentId() {
                            return parentId;
                        }

                        public void setParentId(int parentId) {
                            this.parentId = parentId;
                        }

                        public Object getChildren() {
                            return children;
                        }

                        public void setChildren(Object children) {
                            this.children = children;
                        }
                    }

                    public static class BrandBean {
                        /**
                         * id : 714
                         * nameCn : 喜辽复
                         * nameEn : Hiruscar
                         * nation : 瑞士
                         * type : 2
                         */

                        private int id;
                        private String nameCn;
                        private String nameEn;
                        private String nation;
                        private int type;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getNameCn() {
                            return nameCn;
                        }

                        public void setNameCn(String nameCn) {
                            this.nameCn = nameCn;
                        }

                        public String getNameEn() {
                            return nameEn;
                        }

                        public void setNameEn(String nameEn) {
                            this.nameEn = nameEn;
                        }

                        public String getNation() {
                            return nation;
                        }

                        public void setNation(String nation) {
                            this.nation = nation;
                        }

                        public int getType() {
                            return type;
                        }

                        public void setType(int type) {
                            this.type = type;
                        }
                    }

                    public static class InfosBean {
                        /**
                         * id : 1
                         * name : 肤质通用
                         * productId : 1
                         */

                        private int id;
                        private String name;
                        private int productId;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getProductId() {
                            return productId;
                        }

                        public void setProductId(int productId) {
                            this.productId = productId;
                        }
                    }

                    public static class BaseAttributeBean {
                        /**
                         * id : 56
                         * name : 祛痘能力
                         * score : 0.0
                         */

                        private int id;
                        private String name;
                        private double score;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public double getScore() {
                            return score;
                        }

                        public void setScore(double score) {
                            this.score = score;
                        }
                    }
                }

                public static class AttrsBean {
                    /**
                     * id : 94
                     * questionProductId : 119
                     * attributeId : 56
                     * score : 0.0
                     * attribute : {"id":56,"name":"祛痘能力"}
                     */

                    private int id;
                    private int questionProductId;
                    private int attributeId;
                    private double score;
                    private AttributeBean attribute;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getQuestionProductId() {
                        return questionProductId;
                    }

                    public void setQuestionProductId(int questionProductId) {
                        this.questionProductId = questionProductId;
                    }

                    public int getAttributeId() {
                        return attributeId;
                    }

                    public void setAttributeId(int attributeId) {
                        this.attributeId = attributeId;
                    }

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }

                    public AttributeBean getAttribute() {
                        return attribute;
                    }

                    public void setAttribute(AttributeBean attribute) {
                        this.attribute = attribute;
                    }

                    public static class AttributeBean {
                        /**
                         * id : 56
                         * name : 祛痘能力
                         */

                        private int id;
                        private String name;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }
        }

        public static class UserBeanX {
            /**
             * id : 34183373848578
             * nickname : 爱哔哔测试1
             * portrait : http://image.ibbtech.cn/image/3db1be6a-38c9-4c07-8185-335b8a8757bc.jpg
             * signature : null
             * location : 北京市 北京市 丰台区
             * sex : 1
             * relation : 0
             */

            private long id;
            private String nickname;
            private String portrait;
            private Object signature;
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

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
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

        public static class MentionProductsBean {
            /**
             * id : 119
             * questionId : 152240120549346
             * answerId : null
             * productId : 1
             * score : 0.0
             * product : {"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]}
             * attrs : [{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]
             */

            private int id;
            private long questionId;
            private Object answerId;
            private int productId;
            private double score;
            private ProductBeanX product;
            private List<AttrsBeanX> attrs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getQuestionId() {
                return questionId;
            }

            public void setQuestionId(long questionId) {
                this.questionId = questionId;
            }

            public Object getAnswerId() {
                return answerId;
            }

            public void setAnswerId(Object answerId) {
                this.answerId = answerId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public ProductBeanX getProduct() {
                return product;
            }

            public void setProduct(ProductBeanX product) {
                this.product = product;
            }

            public List<AttrsBeanX> getAttrs() {
                return attrs;
            }

            public void setAttrs(List<AttrsBeanX> attrs) {
                this.attrs = attrs;
            }

            public static class ProductBeanX {
                /**
                 * id : 1
                 * name : Hiruscar 喜辽复 修护凝胶
                 * minPrice : 139.0
                 * maxPrice : 175.0
                 * score : 0.0
                 * favoriteCount : 0
                 * evaluateCount : 0
                 * viewCount : 3
                 * shareCount : 2
                 * infoUrl : http://www.ibbtech.cn/product/21.html
                 * type : 0
                 * favorite : false
                 * category : {"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null}
                 * brand : {"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2}
                 * imgs : ["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"]
                 * infos : [{"id":1,"name":"肤质通用","productId":1}]
                 * baseAttribute : [{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]
                 */

                private int id;
                private String name;
                private double minPrice;
                private double maxPrice;
                private double score;
                private int favoriteCount;
                private int evaluateCount;
                private int viewCount;
                private int shareCount;
                private String infoUrl;
                private int type;
                private boolean favorite;
                private CategoryBeanX category;
                private BrandBeanX brand;
                private List<String> imgs;
                private List<InfosBeanX> infos;
                private List<BaseAttributeBeanX> baseAttribute;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getMinPrice() {
                    return minPrice;
                }

                public void setMinPrice(double minPrice) {
                    this.minPrice = minPrice;
                }

                public double getMaxPrice() {
                    return maxPrice;
                }

                public void setMaxPrice(double maxPrice) {
                    this.maxPrice = maxPrice;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public int getFavoriteCount() {
                    return favoriteCount;
                }

                public void setFavoriteCount(int favoriteCount) {
                    this.favoriteCount = favoriteCount;
                }

                public int getEvaluateCount() {
                    return evaluateCount;
                }

                public void setEvaluateCount(int evaluateCount) {
                    this.evaluateCount = evaluateCount;
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

                public String getInfoUrl() {
                    return infoUrl;
                }

                public void setInfoUrl(String infoUrl) {
                    this.infoUrl = infoUrl;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public boolean isFavorite() {
                    return favorite;
                }

                public void setFavorite(boolean favorite) {
                    this.favorite = favorite;
                }

                public CategoryBeanX getCategory() {
                    return category;
                }

                public void setCategory(CategoryBeanX category) {
                    this.category = category;
                }

                public BrandBeanX getBrand() {
                    return brand;
                }

                public void setBrand(BrandBeanX brand) {
                    this.brand = brand;
                }

                public List<String> getImgs() {
                    return imgs;
                }

                public void setImgs(List<String> imgs) {
                    this.imgs = imgs;
                }

                public List<InfosBeanX> getInfos() {
                    return infos;
                }

                public void setInfos(List<InfosBeanX> infos) {
                    this.infos = infos;
                }

                public List<BaseAttributeBeanX> getBaseAttribute() {
                    return baseAttribute;
                }

                public void setBaseAttribute(List<BaseAttributeBeanX> baseAttribute) {
                    this.baseAttribute = baseAttribute;
                }

                public static class CategoryBeanX {
                    /**
                     * id : 1069
                     * name : 祛痘
                     * icon : null
                     * sequence : 17
                     * parentId : 109
                     * children : null
                     */

                    private int id;
                    private String name;
                    private Object icon;
                    private int sequence;
                    private int parentId;
                    private Object children;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getIcon() {
                        return icon;
                    }

                    public void setIcon(Object icon) {
                        this.icon = icon;
                    }

                    public int getSequence() {
                        return sequence;
                    }

                    public void setSequence(int sequence) {
                        this.sequence = sequence;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public Object getChildren() {
                        return children;
                    }

                    public void setChildren(Object children) {
                        this.children = children;
                    }
                }

                public static class BrandBeanX {
                    /**
                     * id : 714
                     * nameCn : 喜辽复
                     * nameEn : Hiruscar
                     * nation : 瑞士
                     * type : 2
                     */

                    private int id;
                    private String nameCn;
                    private String nameEn;
                    private String nation;
                    private int type;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getNameCn() {
                        return nameCn;
                    }

                    public void setNameCn(String nameCn) {
                        this.nameCn = nameCn;
                    }

                    public String getNameEn() {
                        return nameEn;
                    }

                    public void setNameEn(String nameEn) {
                        this.nameEn = nameEn;
                    }

                    public String getNation() {
                        return nation;
                    }

                    public void setNation(String nation) {
                        this.nation = nation;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }

                public static class InfosBeanX {
                    /**
                     * id : 1
                     * name : 肤质通用
                     * productId : 1
                     */

                    private int id;
                    private String name;
                    private int productId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getProductId() {
                        return productId;
                    }

                    public void setProductId(int productId) {
                        this.productId = productId;
                    }
                }

                public static class BaseAttributeBeanX {
                    /**
                     * id : 56
                     * name : 祛痘能力
                     * score : 0.0
                     */

                    private int id;
                    private String name;
                    private double score;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }
                }
            }

            public static class AttrsBeanX {
                /**
                 * id : 94
                 * questionProductId : 119
                 * attributeId : 56
                 * score : 0.0
                 * attribute : {"id":56,"name":"祛痘能力"}
                 */

                private int id;
                private int questionProductId;
                private int attributeId;
                private double score;
                private AttributeBeanX attribute;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getQuestionProductId() {
                    return questionProductId;
                }

                public void setQuestionProductId(int questionProductId) {
                    this.questionProductId = questionProductId;
                }

                public int getAttributeId() {
                    return attributeId;
                }

                public void setAttributeId(int attributeId) {
                    this.attributeId = attributeId;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public AttributeBeanX getAttribute() {
                    return attribute;
                }

                public void setAttribute(AttributeBeanX attribute) {
                    this.attribute = attribute;
                }

                public static class AttributeBeanX {
                    /**
                     * id : 56
                     * name : 祛痘能力
                     */

                    private int id;
                    private String name;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }

        public static class ProductsBean {
            /**
             * id : 119
             * questionId : 152240120549346
             * answerId : null
             * productId : 1
             * score : 0.0
             * product : {"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":3,"shareCount":2,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":0,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]}
             * attrs : [{"id":94,"questionProductId":119,"attributeId":56,"score":0,"attribute":{"id":56,"name":"祛痘能力"}},{"id":95,"questionProductId":119,"attributeId":57,"score":0,"attribute":{"id":57,"name":"皮肤修护"}},{"id":96,"questionProductId":119,"attributeId":58,"score":0,"attribute":{"id":58,"name":"去印"}},{"id":97,"questionProductId":119,"attributeId":59,"score":0,"attribute":{"id":59,"name":"控油"}},{"id":98,"questionProductId":119,"attributeId":13,"score":0,"attribute":{"id":13,"name":"收缩毛孔"}},{"id":99,"questionProductId":119,"attributeId":6,"score":0,"attribute":{"id":6,"name":"性价比"}},{"id":100,"questionProductId":119,"attributeId":153,"score":0,"attribute":{"id":153,"name":"柔顺"}},{"id":101,"questionProductId":119,"attributeId":154,"score":0,"attribute":{"id":154,"name":"我的"}}]
             */

            private int id;
            private long questionId;
            private Object answerId;
            private int productId;
            private double score;
            private ProductBeanXX product;
            private List<AttrsBeanXX> attrs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getQuestionId() {
                return questionId;
            }

            public void setQuestionId(long questionId) {
                this.questionId = questionId;
            }

            public Object getAnswerId() {
                return answerId;
            }

            public void setAnswerId(Object answerId) {
                this.answerId = answerId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public ProductBeanXX getProduct() {
                return product;
            }

            public void setProduct(ProductBeanXX product) {
                this.product = product;
            }

            public List<AttrsBeanXX> getAttrs() {
                return attrs;
            }

            public void setAttrs(List<AttrsBeanXX> attrs) {
                this.attrs = attrs;
            }

            public static class ProductBeanXX {
                /**
                 * id : 1
                 * name : Hiruscar 喜辽复 修护凝胶
                 * minPrice : 139.0
                 * maxPrice : 175.0
                 * score : 0.0
                 * favoriteCount : 0
                 * evaluateCount : 0
                 * viewCount : 3
                 * shareCount : 2
                 * infoUrl : http://www.ibbtech.cn/product/21.html
                 * type : 0
                 * favorite : false
                 * category : {"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null}
                 * brand : {"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2}
                 * imgs : ["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"]
                 * infos : [{"id":1,"name":"肤质通用","productId":1}]
                 * baseAttribute : [{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]
                 */

                private int id;
                private String name;
                private double minPrice;
                private double maxPrice;
                private double score;
                private int favoriteCount;
                private int evaluateCount;
                private int viewCount;
                private int shareCount;
                private String infoUrl;
                private int type;
                private boolean favorite;
                private CategoryBeanXX category;
                private BrandBeanXX brand;
                private List<String> imgs;
                private List<InfosBeanXX> infos;
                private List<BaseAttributeBeanXX> baseAttribute;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getMinPrice() {
                    return minPrice;
                }

                public void setMinPrice(double minPrice) {
                    this.minPrice = minPrice;
                }

                public double getMaxPrice() {
                    return maxPrice;
                }

                public void setMaxPrice(double maxPrice) {
                    this.maxPrice = maxPrice;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public int getFavoriteCount() {
                    return favoriteCount;
                }

                public void setFavoriteCount(int favoriteCount) {
                    this.favoriteCount = favoriteCount;
                }

                public int getEvaluateCount() {
                    return evaluateCount;
                }

                public void setEvaluateCount(int evaluateCount) {
                    this.evaluateCount = evaluateCount;
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

                public String getInfoUrl() {
                    return infoUrl;
                }

                public void setInfoUrl(String infoUrl) {
                    this.infoUrl = infoUrl;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public boolean isFavorite() {
                    return favorite;
                }

                public void setFavorite(boolean favorite) {
                    this.favorite = favorite;
                }

                public CategoryBeanXX getCategory() {
                    return category;
                }

                public void setCategory(CategoryBeanXX category) {
                    this.category = category;
                }

                public BrandBeanXX getBrand() {
                    return brand;
                }

                public void setBrand(BrandBeanXX brand) {
                    this.brand = brand;
                }

                public List<String> getImgs() {
                    return imgs;
                }

                public void setImgs(List<String> imgs) {
                    this.imgs = imgs;
                }

                public List<InfosBeanXX> getInfos() {
                    return infos;
                }

                public void setInfos(List<InfosBeanXX> infos) {
                    this.infos = infos;
                }

                public List<BaseAttributeBeanXX> getBaseAttribute() {
                    return baseAttribute;
                }

                public void setBaseAttribute(List<BaseAttributeBeanXX> baseAttribute) {
                    this.baseAttribute = baseAttribute;
                }

                public static class CategoryBeanXX {
                    /**
                     * id : 1069
                     * name : 祛痘
                     * icon : null
                     * sequence : 17
                     * parentId : 109
                     * children : null
                     */

                    private int id;
                    private String name;
                    private Object icon;
                    private int sequence;
                    private int parentId;
                    private Object children;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getIcon() {
                        return icon;
                    }

                    public void setIcon(Object icon) {
                        this.icon = icon;
                    }

                    public int getSequence() {
                        return sequence;
                    }

                    public void setSequence(int sequence) {
                        this.sequence = sequence;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public Object getChildren() {
                        return children;
                    }

                    public void setChildren(Object children) {
                        this.children = children;
                    }
                }

                public static class BrandBeanXX {
                    /**
                     * id : 714
                     * nameCn : 喜辽复
                     * nameEn : Hiruscar
                     * nation : 瑞士
                     * type : 2
                     */

                    private int id;
                    private String nameCn;
                    private String nameEn;
                    private String nation;
                    private int type;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getNameCn() {
                        return nameCn;
                    }

                    public void setNameCn(String nameCn) {
                        this.nameCn = nameCn;
                    }

                    public String getNameEn() {
                        return nameEn;
                    }

                    public void setNameEn(String nameEn) {
                        this.nameEn = nameEn;
                    }

                    public String getNation() {
                        return nation;
                    }

                    public void setNation(String nation) {
                        this.nation = nation;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }

                public static class InfosBeanXX {
                    /**
                     * id : 1
                     * name : 肤质通用
                     * productId : 1
                     */

                    private int id;
                    private String name;
                    private int productId;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getProductId() {
                        return productId;
                    }

                    public void setProductId(int productId) {
                        this.productId = productId;
                    }
                }

                public static class BaseAttributeBeanXX {
                    /**
                     * id : 56
                     * name : 祛痘能力
                     * score : 0.0
                     */

                    private int id;
                    private String name;
                    private double score;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }
                }
            }

            public static class AttrsBeanXX {
                /**
                 * id : 94
                 * questionProductId : 119
                 * attributeId : 56
                 * score : 0.0
                 * attribute : {"id":56,"name":"祛痘能力"}
                 */

                private int id;
                private int questionProductId;
                private int attributeId;
                private double score;
                private AttributeBeanXX attribute;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getQuestionProductId() {
                    return questionProductId;
                }

                public void setQuestionProductId(int questionProductId) {
                    this.questionProductId = questionProductId;
                }

                public int getAttributeId() {
                    return attributeId;
                }

                public void setAttributeId(int attributeId) {
                    this.attributeId = attributeId;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public AttributeBeanXX getAttribute() {
                    return attribute;
                }

                public void setAttribute(AttributeBeanXX attribute) {
                    this.attribute = attribute;
                }

                public static class AttributeBeanXX {
                    /**
                     * id : 56
                     * name : 祛痘能力
                     */

                    private int id;
                    private String name;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }
}
