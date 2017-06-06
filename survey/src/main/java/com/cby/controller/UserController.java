package com.cby.controller;

import com.cby.entity.*;
import com.cby.enums.ResultEnum;
import com.cby.repository.AddressRepository;
import com.cby.repository.MyBonusRepository;
import com.cby.repository.MyPointRepository;
import com.cby.repository.UserRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ma on 2017/5/31.
 */
@RestController

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyPointRepository myPointRepository;
    @Autowired
    private MyBonusRepository myBonusRepository;
    @Autowired
    private AddressRepository addressRepository;
    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/regisit")
    public Result regisit(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        List<User> listByUserName = userRepository.findByUserName(user.getUserName());
        if (listByUserName.size() != 0){
            return ResultUtils.error(1,"用户名已存在");
        }
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@Valid User user) {
        List<User> listByUserName = userRepository.findByUserName(user.getUserName());
        if (listByUserName.size() == 0)
            return ResultUtils.error(1, "账号不存在");

        List<User> list = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if (list.size() == 0) {
            return ResultUtils.error(1, "密码错误");
        }
        return ResultUtils.success(list.get(0));
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/login")
    public Result userUpdate(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        } else {
            return ResultUtils.success(userRepository.save(user));
        }
    }

    /**
     * 更新我的积分
     * @param uId
     * @param point
     * @return
     */
    @PostMapping(value = "/updateMyPoint")
    public Result updateMyPoint(@RequestParam("uId") String uId,MyPoint point){
        User user = userRepository.findByUId(uId);
        user.setMyPoint(myPointRepository.save(point));
        return ResultUtils.success(userRepository.save(user));
    }
    /**
     * 更新我的奖金
     * @param uId
     * @param myBonus
     * @return
     */
    @PostMapping(value = "/updateMyBonus")
    public Result updateMyBonus(@RequestParam("uId") String uId,MyBonus myBonus){
        User user = userRepository.findByUId(uId);
        user.setMyBonus(myBonusRepository.save(myBonus));
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     *添加收货地址
     * @param uId
     * @param address
     * @return
     */
    @PostMapping(value = "/addAddress")
    public Result addAddress(@RequestParam("uId") String uId, Address address){
        User user = userRepository.findByUId(uId);
        List<Address> list = user.getAddress();
        list.add(addressRepository.save(address));
        user.setAddress(list);
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 更新收货地址
     * @param address
     * @return
     */
    @PutMapping(value = "/updateAddress")
    public Result updateAddress(@Valid Address address){
        return ResultUtils.success(addressRepository.save(address));
    }

    /**
     * 设置默认收货地址
     * @param uId
     * @param id
     * @return
     */
    @PutMapping(value = "/setDefaultAddress")
    public Result setDefaultAddress(@RequestParam("uId") String uId,@RequestParam("id")Integer id){

        User user = userRepository.findByUId(uId);
        List<Address> list = user.getAddress();
        for (Address address:list) {
            if (address.getId()==id)
                address.setCurrent(true);
            else
                address.setCurrent(false);
        }
        user.setAddress(list);
        return ResultUtils.success(userRepository.save(user));
    }

}
