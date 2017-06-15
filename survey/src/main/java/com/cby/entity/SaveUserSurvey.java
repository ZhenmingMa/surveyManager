package com.cby.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 保存用户已经完成的调研
 * Created by Ma on 2017/6/15.
 */
@Entity
public class SaveUserSurvey {
    @Id
    @GeneratedValue
   private Integer id;
   private String uId;
   @OneToOne
   private Survey survey;

    public SaveUserSurvey() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
