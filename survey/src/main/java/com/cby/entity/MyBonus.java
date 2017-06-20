package com.cby.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 我的奖金
 * Created by Ma on 2017/6/6.
 */
@Entity
public class MyBonus {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private double count;
    private double answer;
    private double invite;
    private double transferred;
    private double auditing;
    private Date time;

    public MyBonus() {
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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double getInvite() {
        return invite;
    }

    public void setInvite(double invite) {
        this.invite = invite;
    }

    public double getTransferred() {
        return transferred;
    }

    public void setTransferred(double transferred) {
        this.transferred = transferred;
    }

    public double getAuditing() {
        return auditing;
    }

    public void setAuditing(double auditing) {
        this.auditing = auditing;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
