package com.cby.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Ma on 2017/5/31.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String uId;
    @NotNull(message = "用户名不能为空")
    @Column(unique = true)
    private String userName;
    @NotNull(message = "密码不能为空")
    private String password;
    private String nikName;
    private String phone;
    private String money;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikName() {
        return nikName;
    }

    public void setNikName(String nikName) {
        this.nikName = nikName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
