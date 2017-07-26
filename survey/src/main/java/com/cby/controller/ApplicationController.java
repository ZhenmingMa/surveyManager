package com.cby.controller;

import com.cby.annotation.UserAccess;
import com.cby.entity.Application;
import com.cby.entity.Result;
import com.cby.entity.User;
import com.cby.service.ApplicationService;
import com.cby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ma on 2017/7/26.
 */
@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @UserAccess
    @PostMapping(value = "/apply")
    public Result apply(String token,Application application){
        User user = UserService.loginUserMap.get(token);
        application.setUserId(user.getId());
        return applicationService.apply(token,application);
    }

}
