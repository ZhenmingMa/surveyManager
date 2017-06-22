package com.cby.entity;

import java.util.List;

/**
 * 返回问卷
 * Created by Ma on 2017/6/20.
 */
public class ResultQuestion {
    private Question question;
    private List<QuestionOption> questionOptions;

    public ResultQuestion() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
