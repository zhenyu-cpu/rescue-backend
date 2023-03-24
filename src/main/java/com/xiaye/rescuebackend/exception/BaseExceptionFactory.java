package com.xiaye.rescuebackend.exception;

import com.xiaye.rescuebackend.types.ResultCodeEnum;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class BaseExceptionFactory {
    @NotNull
    @Contract("null -> fail; !null -> new")
    public static BaseException createException(ResultCodeEnum resultCodeEnum){
        if (resultCodeEnum == null){
            throw new IllegalArgumentException("Enum value cannot be null");
        }
        return new BaseException(resultCodeEnum.code(),resultCodeEnum.message());
    }
}
