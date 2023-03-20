package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import com.xiaye.rescuebackend.vo.AuthParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResultVo<AuthInfoVo> login(@RequestBody AuthParam param) {

        return ResultVo.<AuthInfoVo>builder()
                .code(ResultCodeEnum.SUCCEED.code())
                .build();
    }
    @GetMapping("/")
    public String test(){
        return "test";
    }
}
