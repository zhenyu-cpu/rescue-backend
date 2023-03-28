package com.xiaye.rescuebackend.exception;

import cn.hutool.core.util.StrUtil;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ExceptionFactory {
    @NotNull
    @Contract("null -> fail; !null -> new")
    public static BaseException createException(ResultCodeEnum resultCodeEnum){
        if (resultCodeEnum == null){
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new BaseException(resultCodeEnum.code(),resultCodeEnum.message());
    }

    @NotNull
    @Contract("null -> fail; !null -> new")
    public static AuthException createAuthException(ResultCodeEnum resultCodeEnum){
        if (resultCodeEnum == null){
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new AuthException(resultCodeEnum.code(),resultCodeEnum.message());
    }

    @NotNull
    @Contract("null -> fail; !null -> new")
    public static ParamExceptions createParamException(ResultCodeEnum resultCodeEnum){
        if (resultCodeEnum == null){
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new ParamExceptions(resultCodeEnum.code(),resultCodeEnum.message());
    }

    @NotNull
    @Contract("_ -> new")
    public static BaseException createException(String message){
        if (StrUtil.isBlank(message)){
            throw  new IllegalArgumentException("message value cannot be null");
        }
        return new BaseException(ResultCodeEnum.ERROR.code(), message);
    }
}


/**
 * 我需要创建类，去创建各自所必须的类，他符合google在grpc中对于异常的处理方式
 */
