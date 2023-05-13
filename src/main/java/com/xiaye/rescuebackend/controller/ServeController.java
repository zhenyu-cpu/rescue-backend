package com.xiaye.rescuebackend.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xiaye.rescuebackend.model.Serve;
import com.xiaye.rescuebackend.service.ServeService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.types.ServeStateEnum;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import com.xiaye.rescuebackend.vo.ServeParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/serve")
@Tag(name = "消防服务预约管理")
public class ServeController {
    private final ServeService serveService;
    private final UserService userService;

    @Autowired
    public ServeController(ServeService serveService, UserService userService) {
        this.serveService = serveService;
        this.userService = userService;
    }

    @Operation(summary = "分页获取消防服务预约列表")
    @PostMapping("/list")
    public ResultVo listServeInfo(@Valid @RequestBody PageParam pageParam) {
        Page<Serve> servePage = serveService.page(PageParam.to(pageParam));
        return ResultVo.success(servePage);
    }

    @Operation(summary = "新增和更新消防服务预约")
    @PostMapping("/saveOrUpdate")
    public ResultVo updateCreditApply(@RequestBody @Validated ServeParam serveParam) {
        if (serveParam.isInsert()) {
            serveService.save(ServeParam.convertToServe(serveParam));
            return ResultVo.success("新增消防服务预约");
        }
        serveService.updateById(ServeParam.convertToServe(serveParam));
        return ResultVo.success("更新消防服务预约");
    }

    @GetMapping(value = {"/get"})
    @Operation(summary = "获取消防服务详情")
    public ResultVo getServe(@RequestParam(name = "id", required = true) @NotNull Long id) {
        Serve serve = serveService.getById(id);
        return ResultVo.success(serve);
    }

    @Operation(summary = "删除消防服务预约", description = "只能由管理员权限才能操作")
    @DeleteMapping("/del")
    public ResultVo deleteServe(@RequestBody @NotNull Long id) {
        Boolean result = serveService.removeById(id);
        if (!result) {
            return ResultVo.failure(ResultCodeEnum.RESULT_DATA_NONE);
        }
        return ResultVo.success("删除消防预约成功");
    }

    @Operation(summary = "公司所有的消防服务预约")
    @GetMapping("/companyServes")
    public ResultVo companyServes(@RequestBody @NotNull Long companyId,
                                  @RequestBody @Validated PageParam pageParam) {
        //QueryChainWrapper<Serve> queryChainWrapper = serveService.query();
        LambdaQueryChainWrapper<Serve> queryChainWrapper = serveService.lambdaQuery();
        Page<Serve> page = serveService.page(PageParam.to(pageParam), queryChainWrapper.eq(Serve::getCompanyId, companyId));
        return ResultVo.success(page);
    }

    @PutMapping("/approve")
    @Operation(summary = "审批消防服务申请")
    public ResultVo approveServe(@RequestBody @NotNull Long id,
                                 @RequestBody @NotNull ServeStateEnum state,
                                 @RequestBody String rejMessage) {

        Serve serve = serveService.getById(id);
        serve.setState(state);
        if (!StringUtils.isNullOrEmpty(rejMessage)&& ServeStateEnum.REJECTED.equals(state)) {
            serve.setRejectMessage(rejMessage);
        }
        if (!serveService.save(serve)){
            return ResultVo.failure();
        }
        return ResultVo.success();
    }
}
