package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServeStateEnum {
    RESERVED(0,"已预约"),
    PASSED(1,"已通过"),
    REJECTED(2,"已拒绝"),
    BROKEN_PROMISE(3,"已失信"),
    COMPLETED(4,"已完成");

    /**
     * 服务状态id
     */
    @EnumValue
    @JsonValue
    private final Integer stateId;

    /**
     * 服务状态名
     */
    public String stateName;
}
