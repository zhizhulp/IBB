package com.example.ibb.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/21.
 */

public class PushBean {

    /**
     * productId : 1
     * attrs : [{"attributeId":1},{"attributeId":2}]
     */

    private int productId;
    public List<AttrsBean> attrs = new ArrayList<AttrsBean>();

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<AttrsBean> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrsBean> attrs) {
        this.attrs = attrs;
    }

    public static class AttrsBean {
        /**
         * attributeId : 1
         */

        public int attributeId = 0;

        public int getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(int attributeId) {
            this.attributeId = attributeId;
        }
    }
}
