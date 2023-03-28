package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/creditApply")
@Tag(name = "信用申请管理")
public class CreditApplyController {
    @Operation(summary = "分页获取信用申请列表")
    @PostMapping("/list")
    public ResultVo<Object> listCreditApplies(@Valid PageParam param){
        return ResultVo.builder().build();
    }

    @Operation(summary = "获取信用申请详情",description = "通过id")
    @PostMapping("/get")
    public ResultVo<Object> getCreditApply(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除信用申请记录",description = "通过id")
    @DeleteMapping("/delete")
    public ResultVo<Object> deleteCreditApply(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "更新或插入信用申请记录",description = "更新或者插入信用申请记录")
    @PutMapping("/updateOrSave")
    public ResultVo<Object> updateCreditApply(){
        return ResultVo.builder().build();
    }

    @Operation(summary = "获取公司的信用申请记录",description = "根据公司id，获取公司的所有信用申请记录")
    @PostMapping("/companyApplies")
    public ResultVo<Object> getByCompanyId(@Valid @NotNull Long companyId){
        return ResultVo.builder().build();
    }
}
