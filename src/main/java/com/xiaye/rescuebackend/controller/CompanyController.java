package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/company")
@Tag(name = "公司管理")
public class CompanyController {
    @Operation(summary = "分页获取已注册公司列表")
    @PostMapping("/list")
    public ResultVo<Object> listCompanies(@Valid PageParam param){
        return ResultVo.builder().build();
    }

    @Operation(summary = "获取公司详情信息",description = "通过公司id")
    @PostMapping("/get")
    public ResultVo<Object> getCompany(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除公司",description = "通过公司id，同时删除所有与该公司相关的信息")
    @DeleteMapping("/delete")
    public ResultVo<Object> deleteCompany(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "更新或插入公司信息",description = "更新或者插入公司")
    @PutMapping("/updateOrSave")
    public ResultVo<Object> updateCompany(){
        return ResultVo.builder().build();
    }
}
