package com.xiaye.rescuebackend.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xiaye.rescuebackend.annotation.MultiRequestBody;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.CreditApply;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.service.CreditApplyService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.CreditApplyStateEnum;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.vo.CreditApplyParam;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/creditApply")
@Tag(name = "信用申请管理")
public class CreditApplyController {

    //信用申请管理
    private final CreditApplyService creditApplyService;
    private final CompanyService companyService;
    private final UserService userService;

    @Autowired
    public CreditApplyController(CreditApplyService creditApplyService, CompanyService companyService, UserService userService) {
        this.creditApplyService = creditApplyService;
        this.companyService = companyService;
        this.userService = userService;
    }


    @Operation(summary = "分页获取信用申请列表")
    @PostMapping("/list")
    public ResultVo listCreditApplies(@Valid @RequestBody PageParam param) {
        Page<CreditApply> creditApplyPage = creditApplyService.page(PageParam.to(param));
        return ResultVo.success(creditApplyPage);
    }

    @Operation(summary = "获取信用申请详情", description = "通过id")
    @PostMapping("/get")
    public ResultVo getCreditApply(@RequestParam(name = "id", required = true) @NotNull Long id) {
        CreditApply creditApply = creditApplyService.getById(id);
        return ResultVo.success(creditApply);
    }

    @Operation(summary = "删除信用申请记录", description = "通过id删除信用申请记录")
    @DeleteMapping("/delete")
    public ResultVo deleteCreditApply(@RequestParam(name = "id", required = true) @NotNull Long id) {
        if (!creditApplyService.removeById(id)) {
            return ResultVo.failure(ResultCodeEnum.RESULT_DATA_NONE);
        }
        return ResultVo.success("删除信用申请记录成功");
    }

    @Operation(summary = "更新或插入信用申请记录", description = "更新或者插入信用申请记录")
    @PutMapping("/updateOrSave")
    public ResultVo updateCreditApply(@RequestBody @Validated CreditApplyParam creditApplyParam) {
        if (creditApplyParam.isInsert()) {
            creditApplyService.save(CreditApplyParam.convertToCreditApply(creditApplyParam));
            return ResultVo.success("插入信用申请记录");
        }
        creditApplyService.updateById(CreditApplyParam.convertToCreditApply(creditApplyParam));
        return ResultVo.success("更新信用申请记录");
    }

    @Operation(summary = "获取公司的所有信用申请记录", description = "根据公司id，获取公司的所有信用申请记录")
    @PostMapping("/companyApplies")
    public ResultVo getByCompanyId(@MultiRequestBody @NotNull String companyId,
                                   @MultiRequestBody @Validated PageParam pageParam) {
        Long companyIDL = Long.parseLong(companyId);
        LambdaQueryChainWrapper<CreditApply> queryChainWrapper = creditApplyService.lambdaQuery();
        queryChainWrapper.eq(CreditApply::getCompanyId, companyIDL);
        Page<CreditApply> page = queryChainWrapper.page(PageParam.to(pageParam));
        return ResultVo.success(page);
    }

    @PutMapping("/approval")
    @Operation(summary = "审批信用申请记录")
    @SaCheckRole(value = {RoleNameEnum.SYSTEM_ADMIN_ROLE})
    public ResultVo approvalOfCreditApply(@MultiRequestBody @NotNull Long id,
                                          @MultiRequestBody @NotNull String state,
                                          @MultiRequestBody String rejMessage) {
        CreditApplyStateEnum stateEnum = CreditApplyStateEnum.valueOf(state);
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userService.getById(userId);
        //变更审批状态
        LambdaUpdateChainWrapper<CreditApply> updateChainWrapper = creditApplyService.lambdaUpdate();
        updateChainWrapper.set(CreditApply::getState, stateEnum);
        //不必要参数校验
        if (!StringUtils.isNullOrEmpty(rejMessage) && CreditApplyStateEnum.REJECTED.equals(stateEnum)) {
            updateChainWrapper.set(CreditApply::getRejectMessage, rejMessage);
        }
        updateChainWrapper.set(CreditApply::getApplyuserName, user.getUsername());
        updateChainWrapper.set(CreditApply::getApplyuserPhone, user.getUserPhone());
        updateChainWrapper.set(CreditApply::getApplyTime, LocalDateTime.now());
        updateChainWrapper.eq(CreditApply::getId, id);
        if (!updateChainWrapper.update()) {
            return ResultVo.failure();
        }
        //在公司表中修改信息

        LambdaUpdateChainWrapper<Company> companyLambdaUpdateChainWrapper = companyService.lambdaUpdate();
        //companyLambdaUpdateChainWrapper.set(Company::getCredit,)
        return ResultVo.success();
    }
}
