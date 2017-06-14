package com.cby.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 问卷实体类
 * Created by Ma on 2017/6/13.
 */
@Entity
public class Survey {
    @Id
    @GeneratedValue
    private Integer sId;  //问卷id
    private String tags; //问卷类型
    private String date;        //发布日期
    private String price;      //问卷奖励
    private Integer count;      //数量
    @OneToMany
    private List<Question> questions; //问题列表
    @OneToOne
    private AnswerDemand answerDemand;      //要求

    public Survey() {
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AnswerDemand getAnswerDemand() {
        return answerDemand;
    }

    public void setAnswerDemand(AnswerDemand answerDemand) {
        this.answerDemand = answerDemand;
    }
}
