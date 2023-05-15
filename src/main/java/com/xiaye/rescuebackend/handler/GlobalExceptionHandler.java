package com.xiaye.rescuebackend.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.exception.BaseException;
import com.xiaye.rescuebackend.exception.ParamExceptions;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 权限相关异常处理
     *
     * @param authException
     * @return
     */
    @ExceptionHandler(value = AuthException.class)
    public ResultVo authExceptionHandler(AuthException authException) {
        log.error("AuthException: errorCode({}),errorMessage({})", authException.getCode(), authException.getMessage());
        return ResultVo.failure(ResultCodeEnum.valueOf(authException.getCode()));
    }

    /**
     * 请求校验结果处理,主要是指定类参数限制的参数校验表格
     *
     * @param bindException
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public ResultVo paramExceptionHandler(BindException bindException) {
        BindingResult bindingResult = bindException.getBindingResult();
        return ResultVo.failure(ResultCodeEnum.PARAM_TYPE_BIND_ERROR, bindingResult.getModel());
    }

    /**
     * 持久层和服务层的参数校验错误,该层最常见的就是参数为空
     */
    @ExceptionHandler(value = {ParamExceptions.class})
    public ResultVo paramExceptionHandler(ParamExceptions paramExceptions) {
        return ResultVo.failure(ResultCodeEnum.valueOf(paramExceptions.getCode()));
    }

    /**
     * 参数校验错误，主要是视图层面
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, IllegalArgumentException.class})
    public ResultVo paramExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResultVo.failure(ResultCodeEnum.PARAM_IS_INVALID, methodArgumentNotValidException.getMessage());
    }

    /**
     * 不能登录异常处理
     *
     * @param notLoginException
     * @return
     */
    @ExceptionHandler(value = {NotLoginException.class, NotRoleException.class})
    public ResultVo notLoginExceptionHandler(NotLoginException notLoginException) {
        notLoginException.printStackTrace();
        return ResultVo.failure(ResultCodeEnum.PERMISSION_NO_ACCESS, notLoginException.getMessage());
    }

    /**
     * 其他登陆异常
     *
     * @param baseException
     * @return
     */
    @ExceptionHandler(value = {BaseException.class})
    public ResultVo baseExceptionHandler(BaseException baseException) {
        return ResultVo.failure(baseException.getCode(), baseException.getMessage());
    }
}
