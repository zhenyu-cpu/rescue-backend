package com.xiaye.rescuebackend.controller;


import cn.hutool.core.util.CreditCodeUtil;
import com.xiaye.rescuebackend.types.UserApprovedEnum;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utils")
@Tag(name = "工具接口")
public class UtilsController {
    @Operation(summary = "随机统一社会信用代码")
    @GetMapping("/randomCODS")
    public ResultVo randomCODS() {
        ResultVo result = ResultVo.success();
        result.setData(CreditCodeUtil.randomCreditCode());
        return result;
    }

    @Operation(summary = "测试接口")
    @PostMapping("/test")
    public ResultVo test(@RequestParam UserApprovedEnum approved) {
        return ResultVo.success(approved);
    }
}
