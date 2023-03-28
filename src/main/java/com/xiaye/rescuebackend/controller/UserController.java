package com.xiaye.rescuebackend.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Tag(name = "用户管理", description = "管理用户")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "获取用户信息", description = "通过请求头携带的token,获取用户信息")
    @GetMapping("/info")
    public ResultVo<Object> userInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        UserInfoVo userInfoVo = userService.selectUserInfoById(userId);
        return ResultVo.success(userInfoVo);
    }

    @Operation(summary = "分页获取用户列表", description = "分页获取用户列表")
    @PostMapping("/list")
    public ResultVo<Page<UserInfoVo>> userList(@Valid @RequestBody PageParam param) {
        //获取发起请求的用户id
        Long userId = StpUtil.getLoginIdAsLong();
        Page<UserInfoVo> userInfoVoPage = userService.selectUserPageByUserRole(userId, param);
        return ResultVo.success(userInfoVoPage);
    }

    @Operation(summary = "更新和创建用户")
    @PutMapping("/updateOrSave")
    public ResultVo<Object> updateUserInfo(@Valid @RequestBody UserParam userParam) {
        //插入信息
        if (userParam.isInsert()) {
            return ResultVo.success("创建用户成功");
        }
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除用户信息", description = "通过用户id删除用户信息，接口需要系统管理员角色或者公司管理员权限")
    @PostMapping("/delete")
    @Parameters(value = {
            @Parameter(name = "userId", description = "需要删除的用户id", required = true, in = ParameterIn.QUERY)
    })
    public ResultVo<Object> deleteUser(@Valid @NotNull Long userId) {
        //需要有管理员权限，也就是系统管理员或者公司管理员
        StpUtil.checkRoleOr(RoleNameEnum.SYSTEM_ADMIN.getRoleName(), RoleNameEnum.COMPANY_ADMIN.getRoleName());
        userService.deleteUserByUserIdForDifferentRoles(userId, StpUtil.getLoginIdAsLong());
        return ResultVo.success("删除用户成功");
    }


    @Operation(summary = "修改密码")
    @PostMapping("/updatePassword")
    public ResultVo<Object> updatePassword(UpdatePasswordParam updatePasswordParam) {
        //只能修正当前用户的密码，也就是传入old密码和new密码在数据库中进行更新
        Long userId = StpUtil.getLoginIdAsLong();
        if (!userService.updatePasswordForUser(userId, updatePasswordParam)) {
            return ResultVo.error("更新密码失败");
        }
        return ResultVo.success("更新密码成功");
    }

    @Operation(summary = "审核用户")
    @PostMapping("/audit")
    @Parameters(value = {
            @Parameter(name = "userId", description = "需要通过审核的用户id", required = true, in = ParameterIn.QUERY)
    })
    public ResultVo<Object> auditUsers(@Valid @NotNull Long userId) {
        //该权限只提供给系统管理员
        StpUtil.checkRole(RoleNameEnum.SYSTEM_ADMIN.getRoleName());
        return null;
    }
}
