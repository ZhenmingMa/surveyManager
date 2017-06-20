package com.cby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 我的积分
 * Created by Ma on 2017/6/6.
 */
@Entity
public class MyPoint {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private long count;
    private long login;
    private long share;
    private Date time;

    public MyPoint() {
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getLogin() {
        return login;
    }

    public void setLogin(long login) {
        this.login = login;
    }

    public long getShare() {
        return share;
    }

    public void setShare(long share) {
        this.share = share;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", count=" + count +
                ", login=" + login +
                ", share=" + share +
                ", time=" + time +
                '}';
    }
}
