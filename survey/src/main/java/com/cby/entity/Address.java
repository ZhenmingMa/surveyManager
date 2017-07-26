package com.cby.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 收获地址
 * Created by Ma on 2017/6/6.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private String name;
    private Long phone;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private boolean isCurrent;

    public Address() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
