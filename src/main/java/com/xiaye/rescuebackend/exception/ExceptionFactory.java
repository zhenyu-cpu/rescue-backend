package com.xiaye.rescuebackend.exception;

import com.xiaye.rescuebackend.types.ResultCodeEnum;

public class ExceptionFactory {
    public static BaseException createException(ResultCodeEnum resultCodeEnum) {
        if (resultCodeEnum == null) {
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new BaseException(resultCodeEnum.code(), resultCodeEnum.message());
    }

    public static AuthException createAuthException(ResultCodeEnum resultCodeEnum) {
        if (resultCodeEnum == null) {
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new AuthException(resultCodeEnum.code(), resultCodeEnum.message());
    }

    public static ParamExceptions createParamException(ResultCodeEnum resultCodeEnum) {
        if (resultCodeEnum == null) {
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new ParamExceptions(resultCodeEnum.code(), resultCodeEnum.message());
    }
}


/**
 * 我需要创建类，去创建各自所必须的类，他符合google在grpc中对于异常的处理方式
 */
