package com.cby.service;

import com.cby.entity.*;
import com.cby.repository.AnswerDemandRepo;
import com.cby.repository.QuestionRepository;
import com.cby.repository.SaveUserSurveyRepo;
import com.cby.repository.SurveyRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
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
    @Autowired
    private SaveUserSurveyRepo saveUserSurveyRepo;

    /**
     * 添加问卷
     *
     * @param survey
     * @return
     */
    public Result addSurvey(Survey survey) {
        for (Question question : survey.getQuestions()) {
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
     *
     * @param id
     * @return
     */
    public Result deleteSurvey(Integer id) {
        Survey survey = surveyRepository.findOne(id);
        survey.setAnswerDemand(null);
        surveyRepository.delete(id);
        return ResultUtils.success();
    }

    /**
     * 得到所有问卷
     *
     * @return
     */
    public Result getAllSurvey() {
        return ResultUtils.success(surveyRepository.findAll());
    }

    /**
     * 更新问卷
     *
     * @param survey
     * @return
     */
    public Result updateSurvey(Survey survey) {
        for (Question question : survey.getQuestions()) {
            questionRepository.save(question);
        }
        answerDemandRepo.save(survey.getAnswerDemand());
        return ResultUtils.success(surveyRepository.save(survey));
    }

    /**
     * 通过问卷id查询所有的结果
     * @param id
     * @return
     */
    public Result getSurveyBySId(Integer id) {

        return ResultUtils.success(surveyRepository.findBySId(id));
    }

    /**
     * 提交问卷
     * @param saveUserSurvey
     * @return
     */
    public Result commitSurvey(SaveUserSurvey saveUserSurvey) {
        System.out.println(saveUserSurvey.toString());
        for (Question question : saveUserSurvey.getSurvey().getQuestions()) {
            questionRepository.saveAndFlush(question);
        }
        answerDemandRepo.saveAndFlush(saveUserSurvey.getSurvey().getAnswerDemand());
        surveyRepository.saveAndFlush(saveUserSurvey.getSurvey());

        return ResultUtils.success(saveUserSurveyRepo.save(saveUserSurvey));
    }

    /**
     * 得到当前用户已经完成的调研
     * @param id
     * @return
     */
    public Result getUserSurvey(String id) {
        return ResultUtils.success(saveUserSurveyRepo.findByUId(id));
    }


}
