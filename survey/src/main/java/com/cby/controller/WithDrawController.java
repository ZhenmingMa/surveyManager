package com.cby.controller;

import com.cby.entity.Result;
import com.cby.entity.Withdraw;
import com.cby.service.WithDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付宝提现
 * Created by Ma on 2017/6/26.
 */
@RestController
public class WithDrawController {
@Autowired
private WithDrawService withDrawService;
    @PostMapping(value = "/withdraw")
    public Result withDraw(@RequestParam("token")String token,double count,String alipay){
        return withDrawService.WithDraw(token,count,alipay);
    }
}
