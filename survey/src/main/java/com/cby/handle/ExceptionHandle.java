package com.cby.handle;

import com.cby.entity.Result;
import com.cby.exception.SurveyException;
import com.cby.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ma on 2017/6/1.
 */

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof SurveyException){
            SurveyException surveyException = (SurveyException) e;
            return ResultUtils.error(surveyException.getCode(),surveyException.getMessage());
        }else {
            return ResultUtils.error(-1,e.getMessage());
        }
    }
}
