package com.cby.controller;

import com.cby.annotation.UserAccess;
import com.cby.entity.Address;
import com.cby.entity.Result;
import com.cby.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Ma on 2017/6/19.
 */
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 添加地址
     * @param token
     * @param address
     * @return
     */
    @UserAccess
    @PostMapping(value = "/addAddress")
    public Result addAddress(String token, Address address){
        return addressService.addAddress(token,address);
    }

    /**
     * 更新地址
     * @param token
     * @param address
     * @return
     */
    @UserAccess
    @PostMapping(value = "updateAddress")
    public Result updateAddress(String token,Address address){
        return addressService.updateAddress(token,address);
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "deleteAddress")
    public Result deleteAddress(Integer id){
        return addressService.deleteAddress(id);
    }

    /**
     * 获取所有地址
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "getAllAddress")
    public Result getAllAddress(String token){
        return addressService.getAllAddress(token);
    }

    /**
     * 设置默认收货地址
     * @param token
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "/setDefaultAddress")
    public Result setDefaultAddress(@RequestParam("token") String token, @RequestParam("id") Integer id) {
        return addressService.setDefaultAddress(token,id);
    }
}
