package com.xiaye.rescuebackend.controller;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.annotation.MultiRequestBody;
import com.xiaye.rescuebackend.model.CreditRecord;
import com.xiaye.rescuebackend.service.CreditRecordService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.CreditRecordParam;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResultVo listCreditRecords(@RequestBody @Validated PageParam pageParam) {
        Page<CreditRecord> creditRecordPage = creditRecordService.page(PageParam.to(pageParam));
        return ResultVo.success(creditRecordPage);
    }

    @Operation(summary = "获取信用记录详情")
    @PostMapping("/get")
    public ResultVo getCreditRecord(@RequestParam(name = "id", required = true) @NotNull Long id) {
        CreditRecord creditRecord = creditRecordService.getById(id);
        return ResultVo.success(creditRecord);
    }

    @Operation(summary = "删除信用记录")
    @DeleteMapping("/delete")
    public ResultVo deleteCreditRecord(@RequestParam(name = "id", required = true) Long id) {
        if (!creditRecordService.removeById(id)) {
            return ResultVo.failure(ResultCodeEnum.RESULT_DATA_NONE);
        }
        return ResultVo.success("信用记录删除成功");
    }

    @Operation(summary = "创建和更新信用记录")
    @PutMapping("/saveOrUpdate")
    public ResultVo updateCreditRecord(@RequestBody @Validated CreditRecordParam creditRecordParam) {
        if (creditRecordParam.isInsert()) {
            creditRecordService.save(CreditRecordParam.convertToCreditRecord(creditRecordParam));
            return ResultVo.success("插入信用记录");
        }
        creditRecordService.updateById(CreditRecordParam.convertToCreditRecord(creditRecordParam));
        return ResultVo.success("更新信用记录");
    }

    @Operation(summary = "获取公司的所有信用记录表")
    @PostMapping("/companyRecords")
    public ResultVo companyCreditRecord(@MultiRequestBody @NotNull String companyId,
                                        @MultiRequestBody @Validated PageParam pageParam) {
        //QueryChainWrapper<CreditRecord> queryChainWrapper = creditRecordService.query();
        Long companyIdL = Long.parseLong(companyId);
        LambdaQueryChainWrapper<CreditRecord> queryChainWrapper = creditRecordService.lambdaQuery();
        queryChainWrapper.eq(CreditRecord::getCompanyId, companyIdL);
        Page<CreditRecord> page = queryChainWrapper.page(PageParam.to(pageParam));
        return ResultVo.success(page);
    }
}
