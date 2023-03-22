package com.xiaye.rescuebackend.service;

import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.vo.AuthInfoVo;

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
     * @return
     */
    boolean logout();
}
