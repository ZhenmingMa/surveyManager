package com.cby.enums;

/**
 * Created by Ma on 2017/6/1.
 */
public enum ResultEnum {
    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    NOPERMISSION(100,"token验证失败"),
    ADDRESS_NO_ID(101,"地址id不能为空"),
    ACCOUNT_NO_EXIST(102,"账号不存在"),
    ACCOUNT_HAS_EXIST(103,"账号已存在"),
    PASSWORD_ERROR(104,"密码错误"),
    ADDRESS_ID_NO_EXIST(101,"地址id不存在"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
