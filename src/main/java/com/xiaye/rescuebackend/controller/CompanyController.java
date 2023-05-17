package com.xiaye.rescuebackend.controller;

import com.xiaye.rescuebackend.annotation.MultiRequestBody;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.CreditRecord;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.service.CreditRecordService;
import com.xiaye.rescuebackend.types.CreditRecordStateEnum;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.vo.CompanyParam;
import com.xiaye.rescuebackend.vo.CompanyQueryParam;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/company")
@Tag(name = "公司管理")
public class CompanyController {
    private final CompanyService companyService;
    private final CreditRecordService creditRecordService;

    @Autowired
    public CompanyController(CompanyService companyService, CreditRecordService creditRecordService) {
        this.companyService = companyService;
        this.creditRecordService = creditRecordService;
    }

    @Operation(summary = "分页获取已注册公司列表")
    @Parameters({
            @Parameter(name = "enableAll", description = "是否查询是所有公司，包含未能通过审核的公司", required = true)
    })
    @PostMapping("/list")
    public ResultVo listCompanies(@RequestBody CompanyQueryParam companyQueryParam) {
        if (companyQueryParam.getEnableAll()) {
            return ResultVo.success(companyService.page(PageParam.to(companyQueryParam.getPageParam())));
        }
        return ResultVo.success(companyService.pageCertifiedCompany(PageParam.to(companyQueryParam.getPageParam())));
    }

    @Operation(summary = "获取公司详情信息", description = "通过公司id")
    @GetMapping("/get")
    public ResultVo getCompany(@RequestParam(name = "id", required = true) @NotNull Long id) {
        Company company = companyService.getById(id);
        return ResultVo.success(company);
    }

    @Operation(summary = "删除公司", description = "通过公司id，同时删除所有与该公司相关的信息")
    @DeleteMapping("/delete")
    public ResultVo deleteCompany(@RequestParam(name = "id", required = true) @NotNull Long id) {
        Boolean result = companyService.removeById(id);
        if (!result) {
            return ResultVo.failure(ResultCodeEnum.RESULT_DATA_NONE);
        }
        return ResultVo.success();
    }

    @Operation(summary = "更新或插入公司信息", description = "更新或者插入公司")
    @PutMapping("/updateOrSave")
    public ResultVo updateCompany(@RequestBody @Validated CompanyParam companyParam) {
        if (companyParam.isInsert()) {
            companyService.save(CompanyParam.convertToCompany(companyParam));
            return ResultVo.success("插入公司信息成功");
        }
        companyService.updateById(CompanyParam.convertToCompany(companyParam));
        return ResultVo.success();
    }

    @PutMapping(value = {"/modify", "/credit"})
    @Operation(summary = "修改公司的信用值")
    public ResultVo modifyCompanyCredit(@MultiRequestBody @NotNull String id,
                                        @MultiRequestBody @NotNull String credit,
                                        @MultiRequestBody @NotNull String message) {

        //根据id获取原有的内容
        Company company = companyService.getById(Long.parseLong(id));
        Long oldCredit = company.getCredit();
        Long creditLong = Long.parseLong(credit);
        company.setCredit(creditLong);
        if (!companyService.saveOrUpdate(company)) {
            return ResultVo.failure();
        }

        //信用变更记录表开始记录
        CreditRecord creditRecord = new CreditRecord();

        creditRecord.setCompanyId(company.getId());
        creditRecord.setCreditPre(oldCredit);
        creditRecord.setCreditNext(creditLong);
        creditRecord.setState(oldCredit - creditLong > 0 ? CreditRecordStateEnum.REDUCE : CreditRecordStateEnum.ADDITION);
        creditRecord.setMessage(message);
        if (!creditRecordService.save(creditRecord)) {
            return ResultVo.failure();
        }
        return ResultVo.success();
    }
}
