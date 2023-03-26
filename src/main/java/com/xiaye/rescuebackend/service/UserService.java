package com.xiaye.rescuebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaye.rescuebackend.model.User;

/**
 * 用户服务
 */
public interface UserService extends IService<User> {
    /**
     * 通过手机号码，去查询用户
     *
     * @param phoneNumber 手机号码
     * @return 被查询到的用户
     */
    User selectUserByPhoneNumberAndApproved(String phoneNumber);

    /**
     * 通过手机号码去查询数据库用户，无论用户是否存在数据库
     *
     * @param phoneNumber 手机号码
     * @return 用户
     */
    User selectUserByPhoneNumber(String phoneNumber);

    //手机号存在数据库

    Boolean exitUserByPhoneNumber(String phoneNumber);
}
