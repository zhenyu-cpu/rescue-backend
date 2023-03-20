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
    ERROR(3000, "异常");


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
