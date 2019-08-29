package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/21.
 */

public class FocusBean {
    private QuestionBean question;
    private AnswerBean answer;
    private User user;
    private ReplyBean reply;
    private int type;//123 提问 回答 点赞
    private long time;

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReplyBean getReply() {
        return reply;
    }

    public void setReply(ReplyBean reply) {
        this.reply = reply;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
