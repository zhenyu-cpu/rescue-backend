package com.xiaye.rescuebackend.service;

import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import com.xiaye.rescuebackend.vo.RegisterParam;

/**
 * 登陆服务
 */
public interface AuthService {
    /**
     * 用户登陆
     * @param phoneNumber 手机号码
     * @param password 密码
     * @return 登陆成功的用户信息
     */
    AuthInfoVo login(String phoneNumber, String password) throws AuthException;
    /**
     * 用于注销当前登陆的用户
     * @return 注销登陆信息
     */
    Boolean logout();

    /**
     * 注册用户
     * @param param 注册参数
     * @return 登陆后的用户信息
     * @throws AuthException 认证异常
     */
    Boolean register(RegisterParam param);
}
