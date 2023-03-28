package com.xiaye.rescuebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.UpdatePasswordParam;
import com.xiaye.rescuebackend.vo.UserInfoVo;
import com.xiaye.rescuebackend.vo.UserParam;
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
     *
     * @param id 用户id
     * @return
     */
    UserInfoVo selectUserInfoById(Long id);

    /**
     * 添加公司管理用户
     *
     * @param userParam
     * @return
     */
    User addCompanyAdminUser(UserParam userParam);

    /**
     * 分页查询用户，并获取用户的权限
     *
     * @return 分页查询结果为UserInfoVo
     */
    Page<UserInfoVo> selectUserPageByUserRole(@NotNull Long id, PageParam param);


    /**
     * 审批用户
     *
     * @param id 需要审批的用户id
     * @return 是否成功
     */
    Boolean approveUser(@NotNull Long id);

    /**
     * 为用户更新密码
     *
     * @param userId              需要更新密码的用户id
     * @param updatePasswordParam 参数
     * @return 是否成功
     */
    Boolean updatePasswordForUser(@NotNull Long userId, UpdatePasswordParam updatePasswordParam);


    /**
     * 删除用户，通过用户id，对于不同的用户角色
     *
     * @param userId 需要删除的用户id
     * @param opUserId 操作的用户id
     * @return
     */
    Boolean deleteUserByUserIdForDifferentRoles(@NotNull Long userId, @NotNull Long opUserId);
}
