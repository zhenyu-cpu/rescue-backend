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
    public ResultVo<AuthInfoVo> login(@RequestBody @Validated AuthParam param) {
        AuthInfoVo authInfoVo = authService.login(param.getPhoneNumber(), param.getPassword());
        return ResultVo.<AuthInfoVo>builder()
                .code(ResultCodeEnum.SUCCEED.code())
                .message(ResultCodeEnum.SUCCEED.message())
                .data(authInfoVo)
                .build();
    }

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @Operation(summary = "用户登出")
    @GetMapping("/logout")
    public ResultVo<Object> logout() {
        return ResultVo.success(authService.logout());
    }

    //用户注册
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public ResultVo<String> register(@RequestBody @Validated RegisterParam param) {
        String message = authService.register(param);
        return ResultVo.success(message);
    }
}
