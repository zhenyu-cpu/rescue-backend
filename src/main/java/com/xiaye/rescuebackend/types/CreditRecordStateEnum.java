package com.xiaye.rescuebackend.types;

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
    private Integer stateId;

    /**
     * 信用状态名称
     */
    private String stateName;
}
