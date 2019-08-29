package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/24.
 */

public class HotBean {

    /**
     * id : 1
     * word : iphone
     * begin : null
     * end : null
     * state : 0
     */

    private int id;
    private String word;
    private Object begin;
    private Object end;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Object getBegin() {
        return begin;
    }

    public void setBegin(Object begin) {
        this.begin = begin;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
