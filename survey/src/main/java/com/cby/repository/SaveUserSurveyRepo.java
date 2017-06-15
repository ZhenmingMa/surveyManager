package com.cby.repository;

import com.cby.entity.SaveUserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/6/15.
 */
public interface SaveUserSurveyRepo extends JpaRepository<SaveUserSurvey,Integer>{
    public List<SaveUserSurvey> findByUId(String uId);
}
