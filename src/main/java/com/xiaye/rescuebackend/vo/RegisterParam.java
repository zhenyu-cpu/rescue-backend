package com.xiaye.rescuebackend.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册参数
 */
@Data
public class RegisterParam {
    //用户名
    @NotBlank(message = "用户名不能为空")
    private String username;

    //手机号
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    //密码
    @NotBlank(message = "密码不能为空")
    private String password;

    //公司名
    @NotBlank(message = "公司名不能为空")
    private String companyName;
    //公司地址
    @NotBlank(message = "公司地址不能为空")
    private String companyAddress;
    //公司企业信用码
    @NotBlank(message = "公司企业信用码不能为空")
    private String companyCreditCode;
}
