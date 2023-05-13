package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserApprovedEnum {
    AUDITED(1, "已审核"),
    UNAUDITED(0, "未审核");
    @EnumValue
    @JsonValue
    private final Integer value;
    private final String description;
}
