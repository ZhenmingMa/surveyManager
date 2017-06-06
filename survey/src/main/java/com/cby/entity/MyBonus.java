package com.cby.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 我的奖金
 * Created by Ma on 2017/6/6.
 */
@Entity
public class MyBonus {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition = "0")
    private String answerBonus; //问卷奖励
    @Column(columnDefinition = "0")
    private String inviteBonus; //邀请奖励

    public MyBonus() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerBonus() {
        return answerBonus;
    }

    public void setAnswerBonus(String answerBonus) {
        this.answerBonus = answerBonus;
    }

    public String getInviteBonus() {
        return inviteBonus;
    }

    public void setInviteBonus(String inviteBonus) {
        this.inviteBonus = inviteBonus;
    }
}
