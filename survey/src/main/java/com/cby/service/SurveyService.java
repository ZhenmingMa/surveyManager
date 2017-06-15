package com.cby.service;

import com.cby.entity.AnswerDemand;
import com.cby.entity.Question;
import com.cby.entity.Result;
import com.cby.entity.Survey;
import com.cby.repository.AnswerDemandRepo;
import com.cby.repository.QuestionRepository;
import com.cby.repository.SurveyRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ma on 2017/6/15.
 */
@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerDemandRepo answerDemandRepo;

    /**
     * 添加问卷
     * @param survey
     * @return
     */
    public Result addSurvey(Survey survey){
        for (Question question:survey.getQuestions()){
            questionRepository.save(question);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        survey.setDate(time);
        answerDemandRepo.save(survey.getAnswerDemand());
        return ResultUtils.success(surveyRepository.save(survey));
    }

    /**
     * 删除问卷
     * @param id
     * @return
     */
    public Result deleteSurvey(Integer id){
        Survey survey = surveyRepository.findOne(id);
        survey.setAnswerDemand(null);
        surveyRepository.delete(id);
        return ResultUtils.success();
    }

    /**
     * 得到所有问卷
     * @return
     */
    public Result getAllSurvey(){
        return ResultUtils.success(surveyRepository.findAll());
    }

    /**
     * 更新问卷
     * @param survey
     * @return
     */
    public Result updateSurvey(Survey survey){
        for (Question question:survey.getQuestions()){
            questionRepository.save(question);
        }
        answerDemandRepo.save(survey.getAnswerDemand());
        return ResultUtils.success(surveyRepository.save(survey));
    }
}
