package com.xiaye.rescuebackend.vo;

import lombok.Data;

@Data
public class AuthParam {
    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 密码
     */
    private String password;
}
