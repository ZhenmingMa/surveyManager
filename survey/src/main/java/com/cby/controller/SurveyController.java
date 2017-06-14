package com.cby.controller;

import com.cby.entity.AnswerDemand;
import com.cby.entity.Question;
import com.cby.entity.Result;
import com.cby.entity.Survey;
import com.cby.repository.AnswerDemandRepo;
import com.cby.repository.QuestionRepository;
import com.cby.repository.SurveyRepository;
import com.cby.utils.ResultUtils;
import com.google.gson.Gson;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Ma on 2017/6/13.
 */
@RestController
public class SurveyController {
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
//    @PostMapping(value = "/addSurvey")
//    public Result addSurvey(@RequestBody Survey survey){
//        for (Question question:survey.getQuestions()){
//            System.out.println(question.getContent()+question.getAnswer()+question.getType());
//            questionRepository.save(question);
//        }
//        answerDemandRepo.save(survey.getAnswerDemand());
//        survey.setDate(new Date());
//        return ResultUtils.success(surveyRepository.save(survey));
//    }

    /**
     * 删除问卷
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteSurvey")
    public Result deleteSurvey(@RequestParam("sId") Integer id){
        Survey survey = surveyRepository.findOne(id);
        survey.setAnswerDemand(null);
        surveyRepository.delete(id);
        return ResultUtils.success();
    }

    /**
     * 获取所有的问卷
     * @return
     */
    @GetMapping(value = "/getAllSurvey")
    public Result getAllSurvey(){
        return ResultUtils.success(surveyRepository.findAll());
    }

    @PostMapping(value = "updateSurvey")
    public Result updateSurvey(@RequestBody Survey survey){

        for (Question question:survey.getQuestions()){
            System.out.println(question.getContent()+question.getAnswer()+question.getType());
            questionRepository.save(question);
        }
        answerDemandRepo.save(survey.getAnswerDemand());
        System.out.println(survey.getAnswerDemand().getAge()+survey.getAnswerDemand().getLocation()+survey.getAnswerDemand().getSex());
        return ResultUtils.success(surveyRepository.save(survey));
    }


}
