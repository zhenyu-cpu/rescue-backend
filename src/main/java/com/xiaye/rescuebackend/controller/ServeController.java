package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/serve")
@Tag(name = "消防服务预约管理")
public class ServeController {

    @Operation(summary = "分页获取消防服务预约列表")
    @PostMapping("/list")
    public ResultVo<Object> listServeInfo(@Valid PageParam pageParam){
        return ResultVo.builder().build();
    }

    @Operation(summary = "新增消防服务预约")
    @PostMapping("/add")
    public ResultVo<Object> addServe(){
        return ResultVo.builder().build();
    }

    @Operation(summary = "更新消防服务预约")
    @PutMapping("/update")
    public ResultVo<Object> updateServe(){
        return ResultVo.builder().build();
    }

    @Operation(summary = "删除消防服务预约",description = "只能由管理员权限才能操作")
    @DeleteMapping("/del")
    public ResultVo<Object> deleteServe(@Valid @NotNull Long id){
        return ResultVo.builder().build();
    }

    @Operation(summary = "公司所有的消防服务预约")
    @PostMapping("/companyServes")
    public ResultVo<Object> companyServes(@Valid @NotNull Long companyId){
        return ResultVo.builder().build();
    }
}
