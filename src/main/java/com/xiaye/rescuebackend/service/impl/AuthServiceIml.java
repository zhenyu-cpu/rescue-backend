package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class AuthServiceIml implements AuthService {
    private final UserService userService;

    @Autowired
    public AuthServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String phoneNumber, String password) {
        if (StrUtil.isAllBlank(phoneNumber,password)){
            return null;
        }
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }
}
