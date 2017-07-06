package com.cby.service;

import com.cby.entity.*;
import com.cby.enums.ResultEnum;
import com.cby.repository.ExchangeRepository;
import com.cby.repository.GoodsRepository;
import com.cby.repository.MyPointRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ma on 2017/6/27.
 */
@Service
public class ShopService {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private MyPointRepository myPointRepository;
    @Autowired
    private ExchangeRepository exchangeRepository;
    /**
     * 获取所有的商品信息
     * @return
     */
    public Result getAllGoods(){
        return ResultUtils.success(goodsRepository.findAll());
    }
    /**
     * 添加商品信息
     * @param goods
     * @return
     */
    public Result addGoods( Goods goods){
        return ResultUtils.success(goodsRepository.save(goods));
    }

    /**
     * 通过id更新商品信息
     * @return
     */
    public Result updateGoodsById(Goods goods){
        return ResultUtils.success(goodsRepository.save(goods));
    }
    /**
     * 通过id删除商品
     * @param id
     * @return
     */
    public Result deleteGoodsById(Integer id){
        goodsRepository.delete(id);
        return ResultUtils.success();
    }

    /**
     * 兑换
     * @param token
     * @param id
     * @return
     */
    @Transactional
    public Result exchangeGoods(String token,Integer id){
        User user = UserService.loginUserMap.get(token);
        MyPoint myPoint =myPointRepository.findByUserId(user.getId());
        Goods goods = goodsRepository.findOne(id);
        if (goods.getNumber()==0)
            return ResultUtils.error(ResultEnum.INSUFFICIENT_COUNT);
        if (myPoint.getCount()<goods.getPrice())
            return ResultUtils.error(ResultEnum.INSUFFICIENT_BALANCE);
        goods.setNumber(goods.getNumber()-1);
        goodsRepository.save(goods);
        myPoint.setCount(myPoint.getCount()-goods.getPrice());
        myPointRepository.save(myPoint);
        ExchangeRecord exchangeRecord = new ExchangeRecord();
        exchangeRecord.setGoodsId(goods.getId());
        exchangeRecord.setTime(new Date());
        exchangeRecord.setUserId(user.getId());
        exchangeRecord.setStatus("未发货");
        exchangeRepository.save(exchangeRecord);
        return ResultUtils.success();
    }

    /**
     * 获取用户兑换记录
     * @param token
     * @return
     */
    public Result getExchangeRecord(String token){
        User user = UserService.loginUserMap.get(token);
        List<ExchangeRecord> list = exchangeRepository.findByUserId(user.getId());
        if (list == null || list.size() ==0)
            return ResultUtils.success();
        List<ResultExchangeRecord> list1 = new ArrayList<>();
        for (ExchangeRecord er:list
             ) {
            ResultExchangeRecord resultExchangeRecord = new ResultExchangeRecord();
            resultExchangeRecord.setExchangeRecord(er);
            resultExchangeRecord.setGoods(goodsRepository.findOne(er.getGoodsId()));
            list1.add(resultExchangeRecord);
        }
        return ResultUtils.success(list1);
    }

    public Result deleteExchangeRecord(Integer id){
        exchangeRepository.delete(id);
        return ResultUtils.success();
    }
}
