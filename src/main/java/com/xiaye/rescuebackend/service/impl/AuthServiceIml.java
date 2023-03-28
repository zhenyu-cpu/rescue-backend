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
            throw ExceptionFactory.createException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        User currentUser = userService.selectUserByPhoneNumberAndApproved(phoneNumber);

        if (ObjectUtil.isEmpty(currentUser)){
            throw ExceptionFactory.createAuthException(ResultCodeEnum.USER_AUTH_ERROR);
        }
        //校验密码
        if (!verifyPassword(password, currentUser.getPassword())) {
            throw ExceptionFactory.createException(ResultCodeEnum.USER_AUTH_ERROR);
        }
        StpUtil.login(currentUser.getId());
        return AuthInfoVo.toAuthInfoVo(currentUser, StpUtil.getTokenValue());
    }

    @Override
    public String logout() {
        StpUtil.logout();
        return ResultCodeEnum.USER_LOGOUT.message();
    }

    @Override
    @Transactional
    public String register(RegisterParam param) {
        //保证手机号不存在数据库中
        if (userService.exitUserByPhoneNumber(param.getPhoneNumber())) {
            throw ExceptionFactory.createAuthException(ResultCodeEnum.USER_REGISTER_ERROR);
        }
        //公司信用号码
        if (companyService.exitCompanyByCompanyCode(param.getCompanyCreditCode())) {
            throw ExceptionFactory.createAuthException(ResultCodeEnum.DUPLICATE_COMPANY_REGISTRATION);
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
        user.setApproved(UserApprovedEnum.UNAUDITED.getValue());
        user.setRole(RoleNameEnum.COMPANY_ADMIN.getRoleName());
        user.setCompanyId(company.getId());
        userService.save(user);
        return null;
    }

    /**
     * 验证密码
     * @param inputPassword 输入密码
     * @param storePassword 存储的密码
     * @return 是否相同
     */
    private boolean verifyPassword(String inputPassword, String storePassword) {
        if (ObjectUtil.isEmpty(inputPassword) || ObjectUtil.isEmpty(storePassword)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return inputPassword.equals(storePassword);
    }
}
