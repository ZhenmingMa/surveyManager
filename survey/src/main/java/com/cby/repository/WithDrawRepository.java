package com.cby.repository;

import com.cby.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ma on 2017/6/26.
 */
public interface WithDrawRepository extends JpaRepository<Withdraw,Integer> {
}
