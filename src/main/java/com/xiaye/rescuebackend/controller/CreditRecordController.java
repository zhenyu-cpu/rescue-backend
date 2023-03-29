package com.xiaye.rescuebackend.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.model.CreditRecord;
import com.xiaye.rescuebackend.service.CreditRecordService;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/creditRecord")
@Tag(name = "信用记录管理")
public class CreditRecordController {
    private final CreditRecordService creditRecordService;

    @Autowired
    public CreditRecordController(CreditRecordService creditRecordService) {
        this.creditRecordService = creditRecordService;
    }

    @Operation(summary = "分页获取所有")
    @PostMapping("/list")
    public ResultVo<Object> listCreditRecords(@Valid PageParam pageParam){
        Page<CreditRecord> creditRecordPage = creditRecordService.page(PageParam.to(pageParam));
        return ResultVo.success(creditRecordPage);
    }

    @Operation(summary = "获取信用记录详情")
    @PostMapping("/get")
    public ResultVo<Object> getCreditRecord(@Valid @NotNull @Param(value = "id")  Long id){
        CreditRecord creditRecord = creditRecordService.getById(id);
        return ResultVo.success(creditRecord);
    }

    @Operation(summary = "删除信用记录")
    @DeleteMapping("/delete")
    public ResultVo<Object> deleteCreditRecord(@Valid @NotNull @Param(value = "id") Long id){
        if (!creditRecordService.removeById(id)){
            return ResultVo.error("信用记录删除失败");
        }
        return ResultVo.success("信用记录删除成功");
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
    public ResultVo<Object> companyCreditRecord(@Valid @NotNull @Param(value = "id") Long companyId){
        return ResultVo.builder().build();
    }
}
