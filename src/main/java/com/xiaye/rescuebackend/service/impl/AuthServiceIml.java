package com.xiaye.rescuebackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.exception.AuthException;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class AuthServiceIml  implements AuthService{
    private final UserService userService;

    @Autowired
    public AuthServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthInfoVo login(String phoneNumber, String password) throws AuthException {
        if (StrUtil.isBlank(phoneNumber) || StrUtil.isBlank(password)){
            throw new AuthException(ResultCodeEnum.USER_AUTH_ERROR.code(),ResultCodeEnum.USER_AUTH_ERROR.message());
        }
        User currentUser = userService.selectUserByPhoneNumber(phoneNumber);

        StpUtil.login(currentUser.getId());
        return AuthInfoVo.toAuthInfoVo(currentUser,StpUtil.getTokenValue());
    }

    @Override
    public boolean logout() {
        return false;
    }

    private boolean verifyPassword(String inputPassword, String storePassword) {
        if (ObjectUtil.isEmpty(inputPassword) || ObjectUtil.isEmpty(storePassword)){
            return false;
        }
        return inputPassword.equals(storePassword);
    }
}
