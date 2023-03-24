package com.xiaye.rescuebackend.handler;

import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.exception.BaseException;
import com.xiaye.rescuebackend.exception.ParamExceptions;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 权限相关异常处理
     * @param authException
     * @return
     */
    @ExceptionHandler(value = AuthException.class)
    public ResultVo<Object> authExceptionHandler(@NotNull AuthException authException) {
        log.error("AuthException: errorCode({}),errorMessage({})",authException.getCode(),authException.getMessage());
        return ResultVo
                .<Object>builder()
                .code(authException.getCode())
                .message(authException.getMessage())
                .build();
    }

    /**
     * 请求校验结果处理
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public ResultVo<Object> paramExceptionHandler(@NotNull BindException bindException){
        BindingResult bindingResult = bindException.getBindingResult();
        return ResultVo
                .<Object>builder()
                .code(ResultCodeEnum.PARAM_VERIFY_ERROR.code())
                .message(ResultCodeEnum.PARAM_VERIFY_ERROR.message())
                .data(bindingResult.getModel())
                .build();
    }

    /**
     * 持久层和服务层的参数校验错误
     */
    @ExceptionHandler(value = {ParamExceptions.class})
    public ResultVo<Object> paramExceptionHandler(@NotNull ParamExceptions paramExceptions){
        return ResultVo.<Object>builder().build();
    }
}
