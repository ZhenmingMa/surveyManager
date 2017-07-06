package com.cby.controller;

import com.cby.annotation.UserAccess;
import com.cby.entity.*;
import com.cby.repository.SurveyRecordRepo;
import com.cby.service.SurveyService;
import com.cby.utils.ResultUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToOne;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * 创建问卷
     *
     * @param survey
     * @return
     */
    @PostMapping(value = "/addSurvey")
    public Result addSurvey(Survey survey) {
        return surveyService.addSurvey(survey);
    }

    /**
     * 添加问题
     */
    @PostMapping(value = "/addQuestion")
    public Result addQuestion(Question question) {
        return surveyService.addQuestion(question);
    }

    /**
     * 添加问题选项
     */
    @PostMapping(value = "/addOption")
    public Result addOption(QuestionOption questionOption) {
        return surveyService.addOption(questionOption);
    }

    /**
     * 得到问卷题目
     *
     * @param surveyId
     * @return
     */
    @GetMapping(value = "/getQuestion")
    public Result getQuestion(Integer surveyId) {
        return surveyService.getQuestion(surveyId);
    }


    /**
     * 删除问卷
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteSurvey")
    public Result deleteSurvey(@RequestParam("surveyId") Integer id) {
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
     *
     * @param survey
     * @return
     */
    @PostMapping(value = "/updateSurvey")
    public Result updateSurvey(@RequestBody Survey survey) {
        return surveyService.updateSurvey(survey);
    }

    /**
     * 通过问卷id查询所有的结果
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getSurveyBySId")
    public Result getSurveyBySId(@RequestParam("sId") Integer id) {
        return surveyService.getSurveyBySId(id);
    }

    /**
     * 提交问卷
     *
     * @param
     * @return
     */
    @UserAccess
    @PostMapping(value = "/commitSurvey")
    public Result commitSurvey(@RequestParam(value = "list")String  object ,
                               @RequestParam(value = "token")String token
                               ) {
        return surveyService.commitSurvey(object,token);
    }


    /**
     * 得到当前用户已经完成的调研
     *
     * @param
     * @return
     */
    @UserAccess
    @GetMapping(value = "/getUserSurvey")
    public Result getUserSurvey(@RequestParam("token") String token) {
        return surveyService.getUserSurvey(token);
    }

    /**
     * 得到用户未完成的调研
     * @param token
     * @return
     */
    @UserAccess
    @GetMapping(value = "/getgetUserUnSurvey")
    public Result getUserUnSurvey(String token){
        return surveyService.getUserUnSurvey(token);
    }


}
