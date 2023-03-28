package com.xiaye.rescuebackend.controller;


import cn.hutool.core.util.CreditCodeUtil;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
@Tag(name = "工具接口")
public class UtilsController {
    @Operation(summary = "随机统一社会信用代码")
    @GetMapping("/randomCODS")
    public ResultVo<String> randomCODS() {
        return ResultVo.success(CreditCodeUtil.randomCreditCode());
    }
}
