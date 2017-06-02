package com.cby.repository;

import com.cby.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ma on 2017/6/2.
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
}
