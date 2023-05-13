package com.xiaye.rescuebackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.CreditEnum;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.types.UserApprovedEnum;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import com.xiaye.rescuebackend.vo.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceIml implements AuthService {
    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public AuthServiceIml(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public AuthInfoVo login(String phoneNumber, String password) throws AuthException {
        if (StrUtil.isBlank(phoneNumber) || StrUtil.isBlank(password)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_INVALID);
        }
        User currentUser = userService.selectUserByPhoneNumberAndApproved(phoneNumber);

        if (ObjectUtil.isEmpty(currentUser)){
            throw ExceptionFactory.createAuthException(ResultCodeEnum.USER_NOT_EXIST);
        }
        //校验密码
        if (!verifyPassword(password, currentUser.getPassword())) {
            throw ExceptionFactory.createException(ResultCodeEnum.USER_LOGIN_ERROR);
        }
        StpUtil.login(currentUser.getId());
        return AuthInfoVo.toAuthInfoVo(currentUser, StpUtil.getTokenValue());
    }

    @Override
    public Boolean logout() {
        StpUtil.logout();
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean register(RegisterParam param) {
        //保证手机号不存在数据库中
        if (userService.exitUserByPhoneNumber(param.getPhoneNumber())) {
            throw ExceptionFactory.createAuthException(ResultCodeEnum.USER_HAS_EXISTED);
        }
        //通过公司信用号码保证数据唯一
        if (companyService.exitCompanyByCompanyCode(param.getCompanyCreditCode())) {
            throw ExceptionFactory.createAuthException(ResultCodeEnum.USER_HAS_EXISTED);
        }
        //在数据库中插入公司信息，并返回公司id
        Company company = new Company();
        company.setName(param.getCompanyName());
        company.setAddress(param.getCompanyAddress());
        company.setCode(param.getCompanyCreditCode());
        //初始化公司信用
        company.setCredit(CreditEnum.INITIAL_CREDIT.getValue().longValue());
        companyService.save(company);
        //在数据库中插入用户信息，并返回用户id
        User user = new User();
        user.setUsername(param.getUsername());
        user.setUserPhone(param.getPhoneNumber());
        user.setPassword(param.getPassword());
        user.setApproved(UserApprovedEnum.UNAUDITED);
        user.setRole(RoleNameEnum.COMPANY_ADMIN);
        user.setCompanyId(company.getId());
        return userService.save(user);
    }

    /**
     * 验证密码
     * @param inputPassword 输入密码
     * @param storePassword 存储的密码
     * @return 是否相同
     */
    private boolean verifyPassword(String inputPassword, String storePassword) {
        if (ObjectUtil.isEmpty(inputPassword) || ObjectUtil.isEmpty(storePassword)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return inputPassword.equals(storePassword);
    }
}
