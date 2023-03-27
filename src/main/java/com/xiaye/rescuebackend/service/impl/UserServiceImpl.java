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
import com.xiaye.rescuebackend.vo.UserParam;
import com.xiaye.rescuebackend.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserVo selectUserInfoById(Long id) {
        User  user = baseMapper.selectById(id);
        Company company = companyService.getById(user.getCompanyId());
        return UserVo.of(user,company);
    }

    @Override
    public User addCompanyAdminUser(UserParam userParam) {
        return null;
    }

    @Override
    public Page<User> selectUserPageByUserRole(@NotNull Long id, PageParam param) {
        //通过用户的id
        User curUser = baseMapper.selectById(id);
        return null;
    }
}
