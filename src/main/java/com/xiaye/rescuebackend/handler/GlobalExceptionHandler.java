package com.xiaye.rescuebackend.handler;

import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
    public ResultVo<String> authExceptionHandler(@NotNull AuthException authException) {
        log.error("AuthException: errorCode(%s)");
        return ResultVo
                .<String>builder()
                .code(authException.getCode())
                .message(authException.getMessage())
                .build();
    }
}
