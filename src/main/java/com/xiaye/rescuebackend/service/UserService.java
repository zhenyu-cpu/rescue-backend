package com.xiaye.rescuebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.UserParam;
import com.xiaye.rescuebackend.vo.UserVo;
import org.jetbrains.annotations.NotNull;

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

    /**
     * 通过id，查询用户信息
     * @param id 用户id
     * @return
     */
    UserVo selectUserInfoById(Long id);

    /**
     * 添加公司管理用户
     * @param userParam
     * @return
     */
    User addCompanyAdminUser(UserParam userParam);

    /**
     * 分页查询用户，并获取用户的权限
     */
    Page<User> selectUserPageByUserRole(@NotNull Long id, PageParam param);
}
