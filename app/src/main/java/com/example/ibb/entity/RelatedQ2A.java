package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/17.
 */

public class RelatedQ2A {
    private int id;
    private int type;
    private QuestionBean question;
    private AnswerBean answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
}
