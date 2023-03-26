package com.xiaye.rescuebackend.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CreditApplyStateEnum {

    ALREADY_APPLIED(0,"已申请"),
    REJECTED(1,"已驳回"),
    PASSED(2,"已通过");
    /**
     * 信用事件处理状态id
     */
    private Integer stateId;
    /**
     * 信用事件处理状态名称
     */
    private String  stateName;
}
