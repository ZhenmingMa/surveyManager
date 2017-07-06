package com.cby.controller;

import com.cby.annotation.UserAccess;
import com.cby.entity.Goods;
import com.cby.entity.MyPoint;
import com.cby.entity.Result;
import com.cby.entity.User;
import com.cby.repository.GoodsRepository;
import com.cby.repository.MyPointRepository;
import com.cby.service.ShopService;
import com.cby.service.UserService;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;

/**
 * Created by Ma on 2017/6/2.
 */
@RestController
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * 获取所有的商品信息
     * @return
     */
    @GetMapping(value = "/getAllGoods")
    public Result getAllGoods(){
        return shopService.getAllGoods();
    }

    /**
     * 添加商品信息
     * @param goods
     * @return
     */
    @PostMapping(value = "addGoods")
    public Result addGoods(@Valid Goods goods){
        return shopService.addGoods(goods);
    }

    /**
     * 通过id更新商品信息
     * @return
     */
    @PutMapping(value = "updateGoods")
    public Result updateGoodsById(Goods goods){
        return shopService.updateGoodsById(goods);
    }
    /**
     * 通过id删除商品
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteGoodsById")
    public Result deleteGoodsById(@RequestParam("id")Integer id){
       return shopService.deleteGoodsById(id);
    }

    /**
     * 兑换
     * @param token
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "exchangeGoods")
    public Result exchangeGoods(String token,Integer id){
        return shopService.exchangeGoods(token,id);
    }

    /**
     *获取用户兑换记录
     * @param token
     * @return
     */
    @UserAccess
    @GetMapping(value = "getExchangeRecord")
    public Result getExchangeRecord(String token){
        return shopService.getExchangeRecord(token);
    }

    /**
     * 删除兑换记录
     * @param id
     * @return
     */
    @UserAccess
    @PostMapping(value = "deleteExchangeRecord")
    public Result deleteExchangeRecord(Integer id){
        return shopService.deleteExchangeRecord(id);
    }

}
