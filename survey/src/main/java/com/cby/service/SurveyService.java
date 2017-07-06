package com.cby.service;

import com.cby.entity.*;
import com.cby.repository.*;
import com.cby.utils.ResultUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private MyBonusRepository myBonusRepository;
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
    public Result commitSurvey(String object,String token) {
        User user =UserService.loginUserMap.get(token);
        Survey survey = null;
        try {
            JSONArray jsonArray = new JSONArray(object);
            Date date = new Date();
            Integer id = null;
            for (int i = 0; i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SurveyRecord surveyRecord = new SurveyRecord();
                surveyRecord.setChecked(jsonObject.getBoolean("isChecked"));
                surveyRecord.setSurveyId(jsonObject.getInt("surveyId"));
                surveyRecord.setCustom(jsonObject.optString("custom"));
                surveyRecord.setOptionId(jsonObject.getInt("optionId"));
                surveyRecord.setQuestionId(jsonObject.getInt("questionId"));
                surveyRecord.setUserId(user.getId());
                surveyRecord.setTime(date);
                if (i==0)
                    id = surveyRecord.getSurveyId();
                surveyRecordRepo.save(surveyRecord);
            }
            survey = surveyRepository.findOne(id);
            MyBonus myBonus = myBonusRepository.findByUserId(user.getId());
            myBonus.setAnswer(myBonus.getAnswer()+survey.getBonus());
            myBonus.setCount(myBonus.getCount()+survey.getBonus());
            myBonusRepository.save(myBonus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return ResultUtils.success(survey.getBonus());
    }

    /**
     * 得到当前用户已经完成的调研
     * @param
     * @return
     */
    public Result getUserSurvey(String token) {
        User user =UserService.loginUserMap.get(token);
        List<SurveyRecord> list = surveyRecordRepo.findByUserId(user.getId());
        Set set = new HashSet();
        for (SurveyRecord surveyRecord :list){
            set.add(surveyRecord.getSurveyId());
        }
        List<Survey> list_survey = new ArrayList<>();
        for (Object integer:set){
           list_survey.add( surveyRepository.findOne((Integer) integer));
        }
        return ResultUtils.success(list_survey);
    }










    //得到用户未完成的调研
    public Result getUserUnSurvey(String token){

        User user = UserService.loginUserMap.get(token);
        List<SurveyRecord> list =  surveyRecordRepo.findByUserId(user.getId());
        Set set = new HashSet();
        for (SurveyRecord s:list){
           set.add(s.getSurveyId());
        }
        //得到每一个问卷的问题数量
        for (Object surveyId:set){
            List<Question> questions = questionRepository.findBySurveyId((Integer) surveyId);
        }
        //得到已提交答案的问卷数量
        for (SurveyRecord s:list){
            QuestionOption questionOption = optionRepository.findOne(s.getOptionId());
            questionOption.getQuestionId();
        }



        for (SurveyRecord surveyRecord:list){

        }
        return null;
    }


}
