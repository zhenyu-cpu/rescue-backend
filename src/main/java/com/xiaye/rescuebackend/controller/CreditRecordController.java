package com.xiaye.rescuebackend.controller;


import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/creditRecord")
@Tag(name = "信用记录管理")
public class CreditRecordController {
    @Operation(summary = "分页获取所有")
    @PostMapping("/list")
    public ResultVo<Object> listCreditRecords(){
        return ResultVo.builder().build();
    }

    @Operation(summary = "获取信用记录详情")
    @PostMapping("/get")
    public ResultVo<Object> getCreditRecord(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除信用记录")
    @DeleteMapping("/delete")
    public ResultVo<Object> deleteCreditRecord(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "更新信用记录")
    @PutMapping("/update")
    public ResultVo<Object> updateCreditRecord(){
        return ResultVo.builder().build();
    }

    @Operation(summary = "插入信用记录")
    @PostMapping("/insert")
    public ResultVo<Object> insertCreditRecord(){
        return ResultVo.builder().build();
    }

    @PostMapping("/companyRecords")
    public ResultVo<Object> companyCreditRecord(@Valid @NotNull Long companyId){
        return ResultVo.builder().build();
    }
}
