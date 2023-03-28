package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.mapper.UserMapper;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.UpdatePasswordParam;
import com.xiaye.rescuebackend.vo.UserParam;
import com.xiaye.rescuebackend.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    /**
     * 公司管理服务
     */
    private final CompanyService companyService;

    @Autowired
    public UserServiceImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    public User selectUserByPhoneNumberAndApproved(String phoneNumber){
        if(ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        if(ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public Boolean exitUserByPhoneNumber(String phoneNumber) {
        if (ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return selectUserByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public UserInfoVo selectUserInfoById(Long id) {
        User  user = baseMapper.selectById(id);
        Company company = companyService.getById(user.getCompanyId());
        return UserInfoVo.of(user,company);
    }

    @Override
    public User addCompanyAdminUser(UserParam userParam) {
        return null;
    }

    @Override
    public Page<UserInfoVo> selectUserPageByUserRole(@NotNull Long id, PageParam param) {
        //通过用户的id
        User curUser = baseMapper.selectById(id);
        if(ObjectUtil.isEmpty(curUser)){
            throw ExceptionFactory.createException("用户查询失败，用户id失效");
        }

        Page<UserInfoVo> page = PageParam.to(param);
        //如果是用户的角色为系统管理员,就返回所有公司管理员的分页查询列表
        if (RoleNameEnum.SYSTEM_ADMIN.getRoleName().equals(curUser.getRole())) {
            page = baseMapper.pageUserInfoVoByUserRole(page,RoleNameEnum.COMPANY_ADMIN.getRoleName());
        }
        //如果用户角色为公司管理员，就查看当前公司所有的管理员和员工信息
        //如果用户角色为公司员工，就可以查看用户所在公司的管理员和员工信息
        page = baseMapper.pageUserInfoVoByCompanyId(page,curUser.getCompanyId());
        return page;
    }

    @Override
    public Boolean approveUser(@NotNull Long id) {
        //该函数，只能由系统管理员调用,这部分使用接口鉴权吧
        //保证要被

        return null;
    }

    @Override
    public Boolean updatePasswordForUser(@NotNull Long userId, UpdatePasswordParam updatePasswordParam) {
        //通过userid查找出用户
        User user = baseMapper.selectById(userId);
        //校验old密码和store密码是否一致
        if (!user.getPassword().equals(updatePasswordParam.getOldPassword())){
            return false;
        }
        user.setPassword(updatePasswordParam.getNewPassword());
        return this.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public Boolean deleteUserByUserIdForDifferentRoles(@NotNull Long userId, @NotNull Long opUserId) {
        //添加事务，
        User opUser = baseMapper.selectById(userId);

        return this.removeById(userId);
    }
}
