package com.example.ibb.entity;

import java.util.List;

/**
 * Created by 吕楠 on 2018/4/4.
 */

public class MyEventBusBean_ForADD {

    private List<String> delivery;

    public MyEventBusBean_ForADD(List<String> delivery) {
        this.delivery = delivery;
    }

    public List<String> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<String> delivery) {
        this.delivery = delivery;
    }
}
