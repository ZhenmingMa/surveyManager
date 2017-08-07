package com.cby.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * Created by Ma on 2017/5/31.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String token;
    @NotNull(message = "账号不能为空")
    private long phone;
    @NotNull(message = "密码不能为空")
    private String password;
    private Date time;
    private Date birthday;
    private String location;
    private String occupation;
    private String income;
    private String hobby;
    private String sex;

    public User() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", time=" + time +
                ", birthday=" + birthday +
                ", location='" + location + '\'' +
                ", occupation='" + occupation + '\'' +
                ", income='" + income + '\'' +
                ", hobby='" + hobby + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
