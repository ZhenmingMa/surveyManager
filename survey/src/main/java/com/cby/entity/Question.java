package com.cby.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 问题实体类
 * Created by Ma on 2017/6/13.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer surveyId; //调查表ID
    private Integer type;  //问题类型
    private String content; //问题内容
    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
