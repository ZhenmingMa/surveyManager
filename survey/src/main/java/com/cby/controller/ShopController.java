package com.cby.controller;

import com.cby.entity.Goods;
import com.cby.entity.Result;
import com.cby.repository.GoodsRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ma on 2017/6/2.
 */
@RestController
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 获取所有的商品信息
     * @return
     */
    @GetMapping(value = "/getAllGoods")
    public Result getAllGoods(){
        return ResultUtils.success(goodsRepository.findAll());
    }

    /**
     * 添加商品信息
     * @param goods
     * @return
     */
    @PostMapping(value = "addGoods")
    public Result addGoods(@Valid Goods goods){
        return ResultUtils.success(goodsRepository.save(goods));
    }

    /**
     * 通过id更新商品信息
     * @return
     */
    @PutMapping(value = "updateGoods")
    public Result updateGoodsById(Goods goods){
        return ResultUtils.success(goodsRepository.save(goods));
    }
    /**
     * 通过id删除商品
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteGoodsById")
    public Result deleteGoodsById(@RequestParam("id")Integer id){
        goodsRepository.delete(id);
       return ResultUtils.success();
    }
}
