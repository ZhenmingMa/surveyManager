package com.cby.repository;

import com.cby.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/6/13.
 */

public interface SurveyRepository extends JpaRepository<Survey,Integer>{

    public List<Survey> findBySId(Integer sId);
}
