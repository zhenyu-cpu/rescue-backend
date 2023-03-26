package com.xiaye.rescuebackend.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ParamExceptions extends BaseException{
    /**
     * 异常状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
}
