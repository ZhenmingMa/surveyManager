package com.cby.repository;

import com.cby.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ma on 2017/6/20.
 */
public interface OptionRepository extends JpaRepository<QuestionOption,Integer>{
    public List<QuestionOption> findByQuestionId(Integer integer);
}
