package com.xiaye.rescuebackend.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.ListUtil;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * sa-token权限校验接口扩展
 * @author zhenyu
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    private final UserService userService;

    @Autowired
    public StpInterfaceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //因为loginId是用户id
        Long useId = Long.parseLong(String.valueOf(loginId));
        User user = userService.getById(useId);
        return ListUtil.toList(user.getRole().getRoleName());
    }
}
