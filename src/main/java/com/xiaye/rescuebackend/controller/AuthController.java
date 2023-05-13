package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import com.xiaye.rescuebackend.vo.AuthParam;
import com.xiaye.rescuebackend.vo.RegisterParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "授权管理")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ResultVo login(@RequestBody @Validated AuthParam param) {
        AuthInfoVo authInfoVo = authService.login(param.getPhoneNumber(), param.getPassword());
        return ResultVo.success(authInfoVo);
    }


    @Operation(summary = "用户登出")
    @GetMapping("/logout")
    public ResultVo logout() {
        return authService.logout() ? ResultVo.success() : ResultVo.failure(ResultCodeEnum.SYSTEM_INNER_ERROR);
    }

    //用户注册
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public ResultVo register(@RequestBody @Validated RegisterParam param) {
        return authService.register(param) ? ResultVo.success() : ResultVo.failure(ResultCodeEnum.SYSTEM_INNER_ERROR);
    }
}
