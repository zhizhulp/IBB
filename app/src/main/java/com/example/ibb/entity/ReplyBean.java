package com.example.ibb.entity;


/**
 * Created by ASUS on 2018/5/22.
 * 评论实体类
 */

public class ReplyBean {
    /**
     * id : 41
     * content : 一一一一一
     * answerId : 78
     * replyId : null
     * time : 1526353856000
     * replyUser : null
     * user : {"id":34183373848578,"nickname":"测试人员111","portrait":"http://image.ibbtech.cn/image/8b648aa9-557c-4c23-811f-016c95cfe8de.jpg","signature":"签名不知道怎么填","location":"北京市 北京市 房山区","sex":2,"relation":0}
     * answer : {"id":78,"questionId":152455282288073,"title":"这不是一家烤肉店吗？桌子上怎么没有烤炉.大家有什么推荐的吗？","content":"一个他仍然吞吞吐吐吞吞吐吐","likeCount":1,"replyCount":8,"favoriteCount":0,"shareCount":0,"viewCount":0,"products":[],"time":1524642499000,"user":{"id":34292515602433,"nickname":"MattGeng","portrait":"http://image.ibbtech.cn/image/67b01d03-ab43-4552-92d8-647face279d2.jpg","signature":"特别IBB","location":"北京市 北京市 朝阳区","sex":1,"relation":0}}
     */

    private int id;
    private String content;
    private int answerId;
    private Object replyId;
    private long time;
    private User replyUser;
    private User user;
    private AnswerBean answer;


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

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Object getReplyId() {
        return replyId;
    }

    public void setReplyId(Object replyId) {
        this.replyId = replyId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }



}
