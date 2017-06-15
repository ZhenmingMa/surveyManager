package com.cby.controller;

import com.cby.annotation.UserAccess;
import com.cby.entity.*;
import com.cby.enums.ResultEnum;
import com.cby.repository.AddressRepository;
import com.cby.repository.MyBonusRepository;
import com.cby.repository.MyPointRepository;
import com.cby.repository.UserRepository;
import com.cby.service.UserService;
import com.cby.utils.ResultUtils;
import com.cby.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/regisit")
    public Result regisit(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(ResultEnum.UNKONW_ERROR);
        }
        List<User> listByUserName = userRepository.findByUserName(user.getUserName());
        if (listByUserName.size() != 0) {
            return ResultUtils.error(ResultEnum.ACCOUNT_HAS_EXIST);
        }
        user.setuId(UUIDUtils.id(12));
         MyBonus myBonus = new MyBonus("0","0");
        myBonusRepository.save(myBonus);
        user.setMyBonus(myBonus);
        MyPoint myPoint = new MyPoint("0","0");
        myPointRepository.save(myPoint);
        user.setMyPoint(myPoint);
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
        System.out.println(listByUserName.size());
        if (listByUserName.size() == 0)
            return ResultUtils.error(ResultEnum.ACCOUNT_NO_EXIST);

        List<User> list = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        System.out.println(list.size());
        if (list.size() == 0) {
            return ResultUtils.error(ResultEnum.PASSWORD_ERROR);
        }
        return ResultUtils.success(userService.login(list.get(0)));
    }

    /**
     * 更新用户信息
     *
     * @param bindingResult
     * @return
     */
    @UserAccess
    @PutMapping(value = "/login")
    public Result userUpdate(String token, User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtils.error(101, bindingResult.getFieldError().getDefaultMessage());
        } else {
            User user_map = UserService.loginUserMap.get(token);
            user.setId(user_map.getId());
            return ResultUtils.success(userRepository.save(user));
        }
    }

    @PostMapping(value = "/checkStatus")
    public Result checkStatus(@RequestParam("token") String token){
         return ResultUtils.success( userService.checkStatus(token));
    }


    /**
     * 更新我的积分
     *
     * @param token
     * @param point
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateMyPoint")
    public Result updateMyPoint(@RequestParam("token") String token, MyPoint point) {
        User user = UserService.loginUserMap.get(token);
        user.setMyPoint(myPointRepository.save(point));
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 更新我的奖金
     *
     * @param token
     * @param myBonus
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateMyBonus")
    public Result updateMyBonus(@RequestParam("token") String token, MyBonus myBonus) {
        User user =  UserService.loginUserMap.get(token);
        user.setMyBonus(myBonusRepository.save(myBonus));
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 添加收货地址
     *
     * @param token
     * @param address
     * @return
     */
    @UserAccess
    @PostMapping(value = "/addAddress")
    public Result addAddress(@RequestParam("token") String token, Address address) {
        return ResultUtils.success(userService.addAddress(token, address));
    }

    /**
     * 更新收货地址
     *
     * @param address
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateAddress")
    public Result updateAddress(@RequestParam("token") String token, Address address) {
        if (address.getId() == null)
            return ResultUtils.error(ResultEnum.ADDRESS_NO_ID);
        return ResultUtils.success(userService.updateAddress(token, address));
    }

    /**
     * 设置默认收货地址
     *
     * @param token
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "/setDefaultAddress")
    public Result setDefaultAddress(@RequestParam("token") String token, @RequestParam("id") Integer id) {

        return ResultUtils.success(userService.setDefaultAddress(token,id));
    }

    /**
     * 删除收获地址
     * @param token
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "/deleteAddress")
    public Result deleteAddress(@RequestParam("token") String token, @RequestParam("id") Integer id) {
//           Address address =addressRepository.findOne(id);
//           if (address == null)
//               return ResultUtils.error(ResultEnum.ADDRESS_ID_NO_EXIST);
        return ResultUtils.success(userService.deleteAddress(token,id));
    }

    /**
     * 获取所有收货地址
     * @param token
     * @return
     */
    @UserAccess
    @GetMapping(value = "getAllAddress")
    public Result getALlAddress(@RequestParam("token")String token){
        return ResultUtils.success(userService.getAllAddress(token));
    }
}
