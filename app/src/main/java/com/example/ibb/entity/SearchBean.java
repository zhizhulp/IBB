package com.example.ibb.entity;

import java.util.List;

/**
 * Created by ASUS on 2018/5/24.
 */

public class SearchBean {
    private List<Product> product;
    private List<QuestionBean> question;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<QuestionBean> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionBean> question) {
        this.question = question;
    }
}
