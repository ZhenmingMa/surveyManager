package com.cby.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 问卷记录表
 * Created by Ma on 2017/6/20.
 */
@Entity
public class SurveyRecord {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private Integer surveyId;
    private Integer optionId;
    private Integer questionId;
    private boolean isChecked;
    private String custom;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SurveyRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "SurveyRecord{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", surveyId=" + surveyId +
                ", optionId=" + optionId +
                ", isChecked=" + isChecked +
                ", custom='" + custom + '\'' +
                ", time=" + time +
                '}';
    }
}
