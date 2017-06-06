package com.cby.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户
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
    private String password; //
    private String nicName; //昵称
    private String phone; //电话号码

    @OneToOne
    private MyBonus myBonus; //奖金
    @OneToOne
    private MyPoint myPoint;//积分

    @OneToMany
    private List<Address> address; //收货地址

    public User() {
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

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MyBonus getMyBonus() {
        return myBonus;
    }

    public void setMyBonus(MyBonus myBonus) {
        this.myBonus = myBonus;
    }

    public MyPoint getMyPoint() {
        return myPoint;
    }

    public void setMyPoint(MyPoint myPoint) {
        this.myPoint = myPoint;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
