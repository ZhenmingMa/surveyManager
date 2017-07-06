package com.cby.service;

import com.cby.entity.MyBonus;
import com.cby.entity.Result;
import com.cby.entity.User;
import com.cby.entity.Withdraw;
import com.cby.enums.ResultEnum;
import com.cby.repository.MyBonusRepository;
import com.cby.repository.WithDrawRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.Date;

/**
 * 提现
 * Created by Ma on 2017/6/26.
 */
@Service
public class WithDrawService {
    @Autowired
    private WithDrawRepository withDrawRepository;
    @Autowired
    private MyBonusRepository myBonusRepository;

    public Result WithDraw(String token,double count,String alipay){
        User user = UserService.loginUserMap.get(token);
        MyBonus myBonus = myBonusRepository.findByUserId(user.getId());
        if (myBonus.getCount()<count)
            return ResultUtils.error(ResultEnum.INSUFFICIENT_BALANCE);
        Withdraw withDraw = new Withdraw();
        withDraw.setAlipay(alipay);
        withDraw.setCount(count);
        withDraw.setTime(new Date());
        withDraw.setUserId(user.getId());
        withDrawRepository.save(withDraw);
        myBonus.setCount(myBonus.getCount()-count);
        myBonus.setAuditing(count);
        return ResultUtils.success(myBonusRepository.save(myBonus));
    }
}
