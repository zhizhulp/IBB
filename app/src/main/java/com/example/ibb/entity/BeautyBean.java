package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/23.
 */

public class BeautyBean {

    @Override
    public String toString() {
        return "BeautyBean{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"id":1,"name":"Hiruscar 喜辽复 修护凝胶","minPrice":139,"maxPrice":175,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":2,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/21.html","type":2,"favorite":false,"category":{"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null},"brand":{"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2},"imgs":["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"],"infos":[{"id":1,"name":"肤质通用","productId":1}],"baseAttribute":[{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]},{"id":2,"name":"Yumeijing 郁美净 儿童面霜","minPrice":15,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":0,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/22.html","type":2,"favorite":false,"category":{"id":1059,"name":"面霜","icon":null,"sequence":7,"parentId":109,"children":null},"brand":{"id":1683,"nameCn":"郁美净","nameEn":"Yumeijing","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/郁美净儿童霜.jpg"],"infos":[{"id":2,"name":"儿童安全","productId":2}],"baseAttribute":[{"id":10,"name":"肌肤保湿","score":0},{"id":29,"name":"延展性","score":0},{"id":16,"name":"美白度","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":18,"name":"抗衰老","score":0},{"id":6,"name":"性价比","score":0},{"id":155,"name":"测试","score":0}]},{"id":3,"name":"Pechoin 百雀羚 百雀羚甘油一号","minPrice":10,"maxPrice":30,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":0,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/23.html","type":2,"favorite":false,"category":{"id":1098,"name":"身体护理","icon":null,"sequence":3,"parentId":112,"children":null},"brand":{"id":1236,"nameCn":"百雀羚","nameEn":"Pechoin","nation":"中国","type":2},"imgs":["http://image.ibbtech.cn/image/百雀羚甘油一号.jpg"],"infos":[{"id":3,"name":"干性皮肤","productId":3}],"baseAttribute":[{"id":51,"name":"使用效果","score":0},{"id":1,"name":"温和度","score":0},{"id":4,"name":"清爽度","score":0},{"id":32,"name":"持久度","score":0},{"id":46,"name":"气味","score":0},{"id":6,"name":"性价比","score":0}]},{"id":4,"name":"Beauty Cottage Beauty Cottage 09 口红","minPrice":50,"maxPrice":80,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":0,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/24.html","type":2,"favorite":false,"category":{"id":1123,"name":"口红","icon":null,"sequence":18,"parentId":110,"children":null},"brand":{"id":162,"nameCn":null,"nameEn":"Beauty Cottage","nation":"泰国","type":2},"imgs":["http://image.ibbtech.cn/image/beauty cottage.JPG"],"infos":[{"id":4,"name":"亚光","productId":4}],"baseAttribute":[]},{"id":5,"name":"Wet n Wild 魅力派 口红","minPrice":15,"maxPrice":50,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":0,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/25.html","type":2,"favorite":false,"category":{"id":1123,"name":"口红","icon":null,"sequence":18,"parentId":110,"children":null},"brand":{"id":1646,"nameCn":"魅力派","nameEn":"Wet n wild","nation":"美国","type":2},"imgs":["http://image.ibbtech.cn/image/wet n wild.jpg"],"infos":[{"id":5,"name":"亚光雾面","productId":5}],"baseAttribute":[]},{"id":6,"name":"Relvon 露华浓 口红 ","minPrice":30,"maxPrice":90,"score":0,"favoriteCount":0,"evaluateCount":0,"viewCount":1,"shareCount":0,"infoUrl":"http://www.ibbtech.cn/product/26.html","type":2,"favorite":false,"category":{"id":1123,"name":"口红","icon":null,"sequence":18,"parentId":110,"children":null},"brand":{"id":1332,"nameCn":"露华浓","nameEn":"Revlon","nation":"美国","type":2},"imgs":["http://image.ibbtech.cn/image/露华浓（Revlon）.jpg"],"infos":[{"id":6,"name":"高保湿","productId":6}],"baseAttribute":[]}]
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
        @Override
        public String toString() {
            return "MetaBean{" +
                    "success=" + success +
                    ", message='" + message + '\'' +
                    '}';
        }

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


        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", minPrice=" + minPrice +
                    ", maxPrice=" + maxPrice +
                    ", score=" + score +
                    ", favoriteCount=" + favoriteCount +
                    ", evaluateCount=" + evaluateCount +
                    ", viewCount=" + viewCount +
                    ", shareCount=" + shareCount +
                    ", infoUrl='" + infoUrl + '\'' +
                    ", type=" + type +
                    ", favorite=" + favorite +
                    ", category=" + category +
                    ", brand=" + brand +
                    ", imgs=" + imgs +
                    ", infos=" + infos +
                    ", baseAttribute=" + baseAttribute +
                    '}';
        }

        /**
         * id : 1
         * name : Hiruscar 喜辽复 修护凝胶
         * minPrice : 139
         * maxPrice : 175
         * score : 0
         * favoriteCount : 0
         * evaluateCount : 0
         * viewCount : 2
         * shareCount : 0
         * infoUrl : http://www.ibbtech.cn/product/21.html
         * type : 2
         * favorite : false
         * category : {"id":1069,"name":"祛痘","icon":null,"sequence":17,"parentId":109,"children":null}
         * brand : {"id":714,"nameCn":"喜辽复","nameEn":"Hiruscar","nation":"瑞士","type":2}
         * imgs : ["http://image.ibbtech.cn/image/喜辽复凝胶.jpg"]
         * infos : [{"id":1,"name":"肤质通用","productId":1}]
         * baseAttribute : [{"id":56,"name":"祛痘能力","score":0},{"id":57,"name":"皮肤修护","score":0},{"id":58,"name":"去印","score":0},{"id":59,"name":"控油","score":0},{"id":13,"name":"收缩毛孔","score":0},{"id":6,"name":"性价比","score":0},{"id":153,"name":"柔顺","score":0},{"id":154,"name":"我的","score":0}]
         */

        private boolean check_flg = false;

        public boolean isCheck_flg() {
            return check_flg;
        }

        public void setCheck_flg(boolean check_flg) {
            this.check_flg = check_flg;
        }

        private int id;
        private String name;
        private int minPrice;
        private int maxPrice;
        private int score;
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

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
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
             * score : 0
             */

            private int id;
            private String name;
            private int score;

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

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }
}
