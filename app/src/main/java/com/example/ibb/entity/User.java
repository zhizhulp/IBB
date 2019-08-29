package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/11.
 */

public class User {

    /**
     * id : 34462860836865
     * nickname : iBB-5653
     * portrait : http://image.ibbtech.cn/image/portrait_default.png
     * signature : null
     * location : 海南省省直辖县级行政单位琼中黎族苗族自治县
     * phoneNumber : 15510115653
     * sex : 2
     * follow : 0
     * follower : 0
     * likes : 0
     * relation : 4
     */

    private long id;
    private String nickname;
    private String portrait;
    private String signature;
    private String location;
    private String phoneNumber;
    private int sex;
    private int follow;
    private int follower;
    private int likes;
    private int relation;//01234 没关系/关注/被关注/相互关注/本人

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", portrait='" + portrait + '\'' +
                ", signature=" + signature +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex=" + sex +
                ", follow=" + follow +
                ", follower=" + follower +
                ", likes=" + likes +
                ", relation=" + relation +
                '}';
    }
}
