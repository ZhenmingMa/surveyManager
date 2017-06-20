package com.cby.service;

import com.cby.entity.*;
import com.cby.repository.OptionRepository;
import com.cby.repository.QuestionRepository;
import com.cby.repository.SurveyRecordRepo;
import com.cby.repository.SurveyRepository;
import com.cby.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private OptionRepository optionRepository;
    @Autowired
    private SurveyRecordRepo surveyRecordRepo;
    /**
     * 创建问卷
     *
     * @param survey
     * @return
     */
    public Result addSurvey(Survey survey) {
        return ResultUtils.success(surveyRepository.save(survey));
    }
    /**
     * 添加问题
     * @param question
     * @return
     */
    public Result addQuestion(Question question) {
        return ResultUtils.success(questionRepository.save(question));
    }
    /**
     * 添加问题选项
     */
    public Result addOption(QuestionOption questionOption){
        return  ResultUtils.success(optionRepository.save(questionOption));
    }

    /**
     * 得到问卷题目
     * @param surveyId
     * @return
     */
    public Result getQuestion(Integer surveyId){
        List<ResultQuestion> list_result = new ArrayList<>();
        List<Question> list = questionRepository.findBySurveyId(surveyId);
        for (Question question:list){
            ResultQuestion resultQuestion = new ResultQuestion();
            resultQuestion.setQuestion(question);
            resultQuestion.setQuestionOptions(optionRepository.findByQuestionId(question.getId()));
            list_result.add(resultQuestion);
        }
        return ResultUtils.success(list_result);
    }



    /**
     * 删除问卷
     *
     * @param id
     * @return
     */
    public Result deleteSurvey(Integer id) {
        surveyRepository.delete(id);
        List<Question> list = questionRepository.findBySurveyId(id);
        for (Question question:list){
            questionRepository.delete(question);
              List<QuestionOption> list1=optionRepository.findByQuestionId(question.getId());
              for (QuestionOption questionOption:list1)
                  optionRepository.delete(questionOption);
        }

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

        return ResultUtils.success(surveyRepository.save(survey));
    }

    /**
     * 通过问卷id查询所有的结果
     * @param id
     * @return
     */
    public Result getSurveyBySId(Integer id) {

        return ResultUtils.success();
    }

    /**
     * 提交问卷
     * @param
     * @return
     */
    public Result commitSurvey(ResultSurveyRecord resultSurveyRecord) {
        for (SurveyRecord surveyRecord:resultSurveyRecord.getList())
            surveyRecordRepo.save(surveyRecord);
       return ResultUtils.success(resultSurveyRecord);
    }

    /**
     * 得到当前用户已经完成的调研
     * @param id
     * @return
     */
    public Result getUserSurvey(String id) {
        return null;
    }


}
