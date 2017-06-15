package com.cby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 我的积分
 * Created by Ma on 2017/6/6.
 */
@Entity
public class MyPoint {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "dayPoint",columnDefinition = "varchar(255) not null default '0' ")
    private String dayPoint; //每日登录积分
    @Column(name = "sharePoint",columnDefinition = "varchar(255) not null default '0' ")
    private String sharePoint;//分享积分

    public MyPoint() {
    }

    public MyPoint(String dayPoint, String sharePoint) {
        this.dayPoint = dayPoint;
        this.sharePoint = sharePoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayPoint() {
        return dayPoint;
    }

    public void setDayPoint(String dayPoint) {
        this.dayPoint = dayPoint;
    }

    public String getSharePoint() {
        return sharePoint;
    }

    public void setSharePoint(String sharePoint) {
        this.sharePoint = sharePoint;
    }
}
