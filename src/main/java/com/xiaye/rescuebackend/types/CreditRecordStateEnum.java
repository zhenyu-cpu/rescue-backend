package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CreditRecordStateEnum {
    ADDITION(0,"信用增加"),
    REDUCE(1,"信用减少");
    /**
     * 信用状态id
     */
    @EnumValue
    @JsonValue
    private final Integer stateId;

    /**
     * 信用状态名称
     */
    private final String stateName;
}
