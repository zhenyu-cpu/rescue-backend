package com.xiaye.rescuebackend.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.XCloudDialect;
import com.xiaye.rescuebackend.model.CreditApply;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 信用申请参数
 */
@Data
@NoArgsConstructor
public class CreditApplySaveParam {
    @javax.validation.constraints.NotNull
    private Long companyId;
    //经办人姓名
    @NotBlank
    private String applyUserName;
    //经办人手机号码
    @NotBlank
    private String applyUserPhone;
    //申请时间
    private Date applyTime;

    //失信处罚信息
    @NotBlank
    private String message;
    //整改情况
    @NotBlank
    private String changeMessage;

    //信用承诺
    @NotBlank
    private String promiseMessage;

    @NotNull
    public static CreditApply to(CreditApplySaveParam item){
        CreditApply result = new CreditApply();
        return result;
    }
}
