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
    private Integer id;
    private String name;    //名字
    private double bonus;   //奖金
    private Byte sex;
    private String age;
    private String city;
    private Byte recent;  //最近是否参与过此类调研
    private int questions; //问题数量
    private int count;     //剩余席位
    private Date createTime; //创建时间
    private Date time;     //更新时间

    public Survey() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Byte getRecent() {
        return recent;
    }

    public void setRecent(Byte recent) {
        this.recent = recent;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
