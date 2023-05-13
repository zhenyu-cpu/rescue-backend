package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.CreditApply;
import com.xiaye.rescuebackend.types.CreditApplyStateEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "信用申请请求体")
public class CreditApplyParam {
    // 信用申请id
    private Long id;
    //公司Id
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
    private LocalDateTime applyTime;
    //失信处罚信息
    @NotBlank
    private String message;
    //整改情况
    @NotBlank
    private String changeMessage;

    //信用承诺
    @NotBlank
    private String promiseMessage;

    //信用申请状态
    private CreditApplyStateEnum state;
    //拒绝理由
    private String rejectMessage;

    public static CreditApply convertToCreditApply(CreditApplyParam item) {
        if (item == null) {
            return null;
        }
        CreditApply result = new CreditApply();
        result.setId(item.getId());
        result.setCompanyId(item.getCompanyId());
        result.setApplyuserName(item.getApplyUserName());
        result.setApplyuserPhone(item.getApplyUserPhone());
        result.setApplyTime(item.getApplyTime());
        result.setMessage(item.getMessage());
        result.setChangeMessage(item.getChangeMessage());
        result.setPromiseMessage(item.getPromiseMessage());
        result.setState(item.getState());
        result.setRejectMessage(item.getRejectMessage());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
