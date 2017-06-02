package com.cby.repository;

import com.cby.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/6/1.
 */
public interface VisitRecordRepo extends JpaRepository<VisitRecord,Integer> {
    public List<VisitRecord> findByTimeLike(String today);
}
