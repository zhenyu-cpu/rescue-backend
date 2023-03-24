package com.xiaye.rescuebackend.exception;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException{
    /**
     * 异常状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

}
