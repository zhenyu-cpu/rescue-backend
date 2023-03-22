package com.xiaye.rescuebackend.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthParam {
    /**
     * 手机号码
     */
    @NotNull
    @NotBlank
    private String phoneNumber;

    /**
     * 密码
     */
    @NotNull
    @NotBlank
    private String password;
}
