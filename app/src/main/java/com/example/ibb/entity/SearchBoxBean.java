package com.example.ibb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/22.
 */

public class SearchBoxBean implements Serializable {

    /**
     * meta : {"success":true,"message":"ok"}
     * data : [{"id":12,"name":"Kiko 口红3系哑光金管 ","minPrice":60,"maxPrice":100,"score":0,"favoriteCount":0,"evaluateCount":0,"infoUrl":"http://www.ibbtech.cn/product/32.html","type":0,"category":{"id":1123,"name":"口红","sequence":18,"parentId":110,"children":null},"brand":{"id":853,"nameCn":null,"nameEn":"Kiko","nation":"意大利","type":2},"imgs":["http://image.ibbtech.cn/image/kiko.JPG"],"infos":[{"id":12,"name":"高保湿","productId":12}],"baseAttribute":[]},{"id":4,"name":"Beauty Cottage Beauty Cottage 09 口红","minPrice":50,"maxPrice":80,"score":0,"favoriteCount":0,"evaluateCount":0,"infoUrl":"http://www.ibbtech.cn/product/24.html","type":0,"category":{"id":1123,"name":"口红","sequence":18,"parentId":110,"children":null},"brand":{"id":162,"nameCn":null,"nameEn":"Beauty Cottage","nation":"泰国","type":2},"imgs":["http://image.ibbtech.cn/image/beauty cottage.JPG"],"infos":[{"id":4,"name":"亚光","productId":4}],"baseAttribute":[]},{"id":5,"name":"Wet n Wild 魅力派 口红","minPrice":15,"maxPrice":50,"score":0,"favoriteCount":0,"evaluateCount":0,"infoUrl":"http://www.ibbtech.cn/product/25.html","type":0,"category":{"id":1123,"name":"口红","sequence":18,"parentId":110,"children":null},"brand":{"id":1646,"nameCn":"魅力派","nameEn":"Wet n wild","nation":"美国","type":2},"imgs":["http://image.ibbtech.cn/image/wet n wild.jpg"],"infos":[{"id":5,"name":"亚光雾面","productId":5}],"baseAttribute":[]},{"id":6,"name":"Relvon 露华浓 口红 ","minPrice":30,"maxPrice":90,"score":0,"favoriteCount":0,"evaluateCount":0,"infoUrl":"http://www.ibbtech.cn/product/26.html","type":0,"category":{"id":1123,"name":"口红","sequence":18,"parentId":110,"children":null},"brand":{"id":1332,"nameCn":"露华浓","nameEn":"Revlon","nation":"美国","type":2},"imgs":["http://image.ibbtech.cn/image/露华浓（Revlon）.jpg"],"infos":[{"id":6,"name":"高保湿","productId":6}],"baseAttribute":[]}]
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

    public static class MetaBean implements Serializable {
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

    public static class DataBean implements Serializable {
        /**
         * id : 12
         * name : Kiko 口红3系哑光金管
         * minPrice : 60.0
         * maxPrice : 100.0
         * score : 0.0
         * favoriteCount : 0
         * evaluateCount : 0
         * infoUrl : http://www.ibbtech.cn/product/32.html
         * type : 0
         * category : {"id":1123,"name":"口红","sequence":18,"parentId":110,"children":null}
         * brand : {"id":853,"nameCn":null,"nameEn":"Kiko","nation":"意大利","type":2}
         * imgs : ["http://image.ibbtech.cn/image/kiko.JPG"]
         * infos : [{"id":12,"name":"高保湿","productId":12}]
         * baseAttribute : []
         */

        private int id;
        private String name;
        private double minPrice;
        private double maxPrice;
        private double score;
        private int favoriteCount;
        private int evaluateCount;
        private String infoUrl;
        private int type;
        private CategoryBean category;
        private BrandBean brand;
        private List<String> imgs;
        private List<InfosBean> infos;
        private List<?> baseAttribute;

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

        public List<?> getBaseAttribute() {
            return baseAttribute;
        }

        public void setBaseAttribute(List<?> baseAttribute) {
            this.baseAttribute = baseAttribute;
        }

        public static class CategoryBean implements Serializable {
            /**
             * id : 1123
             * name : 口红
             * sequence : 18
             * parentId : 110
             * children : null
             */

            private int id;
            private String name;
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

        public static class BrandBean implements Serializable {
            /**
             * id : 853
             * nameCn : null
             * nameEn : Kiko
             * nation : 意大利
             * type : 2
             */

            private int id;
            private Object nameCn;
            private String nameEn;
            private String nation;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getNameCn() {
                return nameCn;
            }

            public void setNameCn(Object nameCn) {
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

        public static class InfosBean implements Serializable {
            /**
             * id : 12
             * name : 高保湿
             * productId : 12
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
    }
}
