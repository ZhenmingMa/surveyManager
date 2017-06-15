package com.cby.controller;

import com.cby.entity.*;
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

    /**
     * 更新问卷
     * @param survey
     * @return
     */
    @PostMapping(value = "/updateSurvey")
    public Result updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }

    /**
     *通过问卷id查询所有的结果
     * @param id
     * @return
     */
    @PostMapping(value = "/getSurveyBySId")
    public Result getSurveyBySId(@RequestParam("sId") Integer id) {
        return surveyService.getSurveyBySId(id);
    }

    /**
     * 提交问卷
     * @param saveUserSurvey
     * @return
     */
    @PostMapping(value = "/commitSurvey")
    public Result commitSurvey(@RequestBody SaveUserSurvey saveUserSurvey) {
        return surveyService.commitSurvey(saveUserSurvey);
    }

    /**
     * 得到当前用户已经完成的调研
     * @param id
     * @return
     */
    @GetMapping(value = "/getUserSurvey")
    public Result getUserSurvey(@RequestParam("uId") String id) {
        return surveyService.getUserSurvey(id);
    }

}
