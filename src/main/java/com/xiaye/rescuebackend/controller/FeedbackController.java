package com.xiaye.rescuebackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.model.Feedback;
import com.xiaye.rescuebackend.service.FeedbackService;
import com.xiaye.rescuebackend.vo.FeedbackParam;
import com.xiaye.rescuebackend.vo.FeedbackVo;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/feedback")
@Tag(name = "信息反馈", description = "匿名信息反馈接口")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/get")
    @Operation(summary = "通过id查询反馈信息", method = "POST")
    @Parameters(value = {
            @Parameter(name = "id", description = "传入信息的id", required = true, in = ParameterIn.QUERY)
    })
    public ResultVo<FeedbackVo> feedbackById(@Valid @NotBlank @Param(value = "id") Long id) {
        FeedbackVo feedbackVo = FeedbackVo.of(feedbackService.getById(id));
        return ResultVo.success(feedbackVo);
    }

    @Operation(summary = "更新或插入反馈信息",method = "PUT")
    @PutMapping("/saveOrUpdate")
    public ResultVo<String> update(@Valid @RequestBody FeedbackParam feedbackParam) {
        //不是插入就保存信息
        if (feedbackParam.isInsert()){
            feedbackService.saveOrUpdate(FeedbackParam.to(feedbackParam));
            return ResultVo.success("插入信息成功");
        }
        feedbackService.saveOrUpdate(FeedbackParam.to(feedbackParam));
        return ResultVo.success("更新信息成功");
    }

    @Operation(summary = "删除反馈信息",method = "DELETE")
    @DeleteMapping("/delete")
    public ResultVo<String> delete(@Valid @NotBlank @Param(value = "id") Long id){
       boolean result =  feedbackService.removeById(id);
       if (result){
           return ResultVo.success("删除反馈信息成功");
       }
       return ResultVo.success("删除反馈信息失败");
    }

    @Operation(summary = "分页查询反馈信息",method = "POST")
    @PostMapping("/list")
    public ResultVo<Page<Feedback>> list(@Valid @RequestBody PageParam param){
        Page<Feedback> feedbackPage = feedbackService.page(PageParam.to(param));
        return ResultVo.success(feedbackPage);
    }
}