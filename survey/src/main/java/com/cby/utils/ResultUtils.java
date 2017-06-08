package com.cby.utils;

import com.cby.entity.Result;
import com.cby.enums.ResultEnum;

/**
 * Created by Ma on 2017/6/1.
 */
public class ResultUtils {
    /**
     * 成功带参数的返回结果
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    /**
     * 成功不带参数的返回结果
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败的返回结果
     *
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
