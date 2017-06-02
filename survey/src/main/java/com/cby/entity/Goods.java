package com.cby.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 商城货物信息
 * Created by Ma on 2017/6/2.
 */
@Entity
public class Goods {

    @Id
    @GeneratedValue
    private Integer id; //商品ID
    @NotNull(message = "商品图片不能为空")
    private String img; //商品图片
    @NotNull
    private String name;//商品名称
    @NotNull
    private String time;//开放购买时间
    @NotNull
    private String number;//商品数量
    @NotNull
    private Integer price;//商品价格

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Goods() {

    }
}
