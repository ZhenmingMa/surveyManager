package com.cby.controller;

import com.cby.entity.AnswerDemand;
import com.cby.entity.Question;
import com.cby.entity.Result;
import com.cby.entity.Survey;
import com.cby.repository.AnswerDemandRepo;
import com.cby.repository.QuestionRepository;
import com.cby.repository.SurveyRepository;
import com.cby.service.SurveyService;
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
    private SurveyService surveyService;


    /**
     * 添加问卷
     *
     * @param survey
     * @return
     */
    @PostMapping(value = "/addSurvey")
    public Result addSurvey(@RequestBody Survey survey) {
        return surveyService.addSurvey(survey);
    }

    /**
     * 删除问卷
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteSurvey")
    public Result deleteSurvey(@RequestParam("sId") Integer id) {
        return surveyService.deleteSurvey(id);
    }

    /**
     * 获取所有的问卷
     *
     * @return
     */
    @GetMapping(value = "/getAllSurvey")
    public Result getAllSurvey() {
        return surveyService.getAllSurvey();
    }

    @PostMapping(value = "updateSurvey")
    public Result updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }

}
