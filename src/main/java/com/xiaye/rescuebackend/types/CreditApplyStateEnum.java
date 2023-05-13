package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
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
    @EnumValue
    @JsonValue
    private final Integer stateId;
    /**
     * 信用事件处理状态名称
     */
    private final String  stateName;
}
