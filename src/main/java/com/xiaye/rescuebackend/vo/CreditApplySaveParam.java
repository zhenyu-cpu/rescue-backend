package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.CreditApply;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Optional;

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

    /**
     * 信用处理时间
     */
    @javax.validation.constraints.NotNull
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
    public static CreditApply to(@NotNull CreditApplySaveParam item){
        CreditApply result = new CreditApply();
        Optional.ofNullable(item.getCompanyId()).ifPresent(result::setCompanyId);
        Optional.ofNullable(item.getApplyUserName()).ifPresent(result::setApplyuserName);
        Optional.ofNullable(item.getApplyUserPhone()).ifPresent(result::setApplyuserPhone);
        Optional.ofNullable(item.getMessage()).ifPresent(result::setMessage);
        Optional.ofNullable(item.getChangeMessage()).ifPresent(result::setChangeMessage);
        Optional.ofNullable(item.getPromiseMessage()).ifPresent(result::setPromiseMessage);
        return result;
    }
}
