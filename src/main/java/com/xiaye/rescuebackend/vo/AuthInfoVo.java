package com.xiaye.rescuebackend.vo;


import lombok.Data;

@Data
public class AuthInfoVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 身份验证的token
     */
    private String token;

    /**
     * 用户手机号，这里需要做脱敏处理
     */
    private String phoneNumber;
}
