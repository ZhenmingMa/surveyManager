package com.cby.repository;

import com.cby.entity.ExchangeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Ma on 2017/6/27.
 */
public interface ExchangeRepository extends JpaRepository<ExchangeRecord,Integer>{
    public List<ExchangeRecord> findByUserId(String userId);
}
