package com.cby.controller;

import com.cby.entity.Result;
import com.cby.entity.User;
import com.cby.enums.ResultEnum;
import com.cby.repository.UserRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ma on 2017/5/31.
 */
@RestController

public class UserController {
    @Autowired
    private UserRepository userRepository;

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
}
