package com.cby.exception;

import com.cby.enums.ResultEnum;

/**
 * 集中处理异常
 * Created by Ma on 2017/6/1.
 */

public class SurveyException extends RuntimeException {
    private Integer code;

    public SurveyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
