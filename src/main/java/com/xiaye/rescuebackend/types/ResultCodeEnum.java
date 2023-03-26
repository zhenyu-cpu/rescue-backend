package com.xiaye.rescuebackend.types;

/**
 * 返回的状态码
 * 1000 ~ 1999:参数错误
 * 2000 ~ 2999:用户异常
 * 3000 ~ 3999:接口异常
 */
public enum ResultCodeEnum {
    /**
     * 请求成功
     */
    SUCCEED(999, "成功"),
    /**
     * 请求异常
     */
    ERROR(3000, "异常"),
    /**
     * 参数异常
     */
    PARAM_VERIFY_ERROR(1001,"参数异常"),
    /**
     * 用户授权成功
     */
    USER_AUTH_SUCCEED(2000,"用户授权成功"),
    /**
     * 用户授权失败
     */
    USER_AUTH_ERROR(2001,"用户授权失败"),

    /**
     * 用户未能通过审核
     */
    USER_NOT_APPROVED(20002,"用户未通过审核"),
    /**
     * 未能找到用户
     */
    USER_NOT_FOUND(2003,"未能发现用户"),
    /**
     * 用户未登录
     */
    USER_NOT_AUTH(2004,"用户未登录"),
    /**
     * 用户已经注销
     */
    USER_LOGOUT(2005,"用户已注销"),
    /**
     * 用户注册失败
     */
    USER_REGISTER_ERROR(2006,"用户注册失败"),

    //公司以及被注册
    DUPLICATE_COMPANY_REGISTRATION(3000,"公司重复注册");


    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
