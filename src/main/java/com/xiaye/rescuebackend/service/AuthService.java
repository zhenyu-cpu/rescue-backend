package com.xiaye.rescuebackend.service;

import com.xiaye.rescuebackend.model.User;

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
    User login(String phoneNumber,String password);

    /**
     * 用于注销当前登陆的用户
     * @return
     */
    boolean logout();
}
