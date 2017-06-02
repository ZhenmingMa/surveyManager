package com.cby.controller;

import com.cby.entity.Goods;
import com.cby.entity.Result;
import com.cby.repository.GoodsRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Ma on 2017/6/2.
 */
@RestController
public class ShopController {
    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping(value = "/getAllGoods")
    public Result getAllGoods(){
        return ResultUtils.success(goodsRepository.findAll());
    }
    @PostMapping(value = "addGoods")
    public Result addGoods(@Valid Goods goods){
        return ResultUtils.success(goodsRepository.save(goods));
    }
}
