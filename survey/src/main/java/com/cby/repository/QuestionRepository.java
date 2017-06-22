package com.cby.repository;

import com.cby.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 问卷
 * Created by Ma on 2017/6/13.
 */
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    public List<Question> findBySurveyId(Integer surveyId);
}
