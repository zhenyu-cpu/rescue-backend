package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.service.AuthService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.AuthInfoVo;
import com.xiaye.rescuebackend.vo.AuthParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
