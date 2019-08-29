package com.example.ibb.adapter;

/**
 * Created by 张凯雅 on 2018/2/24.
 */

public class TwoBeen {
    private String name;
    private int age;
    private String sex;

    public TwoBeen() {
    }

    public TwoBeen(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
