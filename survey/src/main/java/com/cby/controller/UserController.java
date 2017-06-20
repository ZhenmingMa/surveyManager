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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/regisit")
    public Result regisit(@Valid User user, BindingResult bindingResult) {
        return userService.regisit(user, bindingResult);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@Valid User user,BindingResult bindingResult) {
        return userService.login(user,bindingResult);
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
        return userService.userUpdate(token, user, bindingResult);
    }

    /**
     * 检查登录状态
     * @param token
     * @return
     */
    @PostMapping(value = "/checkStatus")
    public Result checkStatus(@RequestParam("token") String token){
         return userService.checkStatus(token);
    }

    /**
     * 刷新每日登录积分
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateLoginMyPoint")
    public Result updateLoginMyPoint(String token){
        return userService.updateLoginMyPoint(token);
    }

    /**
     * 刷新分享积分
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateShareMyPoint")
    public Result updateShareMyPoint(String token){
        return userService.updateShareMyPoint(token);
    }

    /**
     * 获取我的积分信息
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "/getMyPoint")
    public Result getMyPoint(String token){
        return userService.getMyPoint(token);
    }


    /**
     * 更新我的问卷回答奖金
     *
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateAnswerMyBonus")
    public Result updateAnswerMyBonus(@RequestParam("token") String token,double answer) {
       return userService.updateAnswerMyBonus(token,answer);
    }
    /**
     * 更新我的好友邀请奖金
     *
     * @param token
     * @return
     */
    @UserAccess
    @PostMapping(value = "/updateInviteMyBonus")
    public Result updateInviteMyBonus(@RequestParam("token") String token) {
        return userService.updateInviteMyBonus(token);
    }


    @UserAccess
    @PostMapping(value = "/getMyBonus")
    public Result getMyBonus(@RequestParam("token") String token) {
        return userService.getMybonus(token);
    }
//
//    /**
//     * 添加收货地址
//     *
//     * @param token
//     * @param address
//     * @return
//     */
//    @UserAccess
//    @PostMapping(value = "/addAddress")
//    public Result addAddress(@RequestParam("token") String token, Address address) {
//        return ResultUtils.success(userService.addAddress(token, address));
//    }
//
//    /**
//     * 更新收货地址
//     *
//     * @param address
//     * @return
//     */
//    @UserAccess
//    @PostMapping(value = "/updateAddress")
//    public Result updateAddress(@RequestParam("token") String token, Address address) {
//        if (address.getId() == null)
//            return ResultUtils.error(ResultEnum.ADDRESS_NO_ID);
//        return ResultUtils.success(userService.updateAddress(token, address));
//    }
//
//    /**
//     * 设置默认收货地址
//     *
//     * @param token
//     * @param id
//     * @return
//     */
//    @UserAccess
//    @PostMapping(value = "/setDefaultAddress")
//    public Result setDefaultAddress(@RequestParam("token") String token, @RequestParam("id") Integer id) {
//
//        return ResultUtils.success(userService.setDefaultAddress(token,id));
//    }
//
//    /**
//     * 删除收获地址
//     * @param token
//     * @param id
//     * @return
//     */
//    @UserAccess
//    @PostMapping(value = "/deleteAddress")
//    public Result deleteAddress(@RequestParam("token") String token, @RequestParam("id") Integer id) {
////           Address address =addressRepository.findOne(id);
////           if (address == null)
////               return ResultUtils.error(ResultEnum.ADDRESS_ID_NO_EXIST);
//        return ResultUtils.success(userService.deleteAddress(token,id));
//    }
//
//    /**
//     * 获取所有收货地址
//     * @param token
//     * @return
//     */
//    @UserAccess
//    @GetMapping(value = "getAllAddress")
//    public Result getALlAddress(@RequestParam("token")String token){
//        return ResultUtils.success(userService.getAllAddress(token));
//    }
}
