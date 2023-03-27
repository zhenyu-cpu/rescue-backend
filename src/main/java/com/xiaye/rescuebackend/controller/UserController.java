package com.xiaye.rescuebackend.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import com.xiaye.rescuebackend.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        UserVo userVo = userService.selectUserInfoById(userId);
        return ResultVo.success(userVo);
    }

    @Operation(summary = "获取用户信息", description = "根据查询条件获取用户信息")
    @PostMapping("/list")
    public ResultVo<Object> userList(@Valid @RequestBody PageParam param) {
        //获取发起请求的用户id
        Long userId  = StpUtil.getLoginIdAsLong();

        return ResultVo.builder().build();
    }

    @Operation(summary = "更新和创建用户")
    @PostMapping("/updateOrSave")
    public ResultVo<Object> updateUserInfo() {
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除用户信息")
    @PostMapping("/delete")
    public ResultVo<Object> deleteUser() {
        return ResultVo.builder().build();
    }


    @Operation(summary = "修改密码")
    @PostMapping("/updatePassword")
    public ResultVo<Object> updatePassword() {
        return ResultVo.builder().build();
    }
}
