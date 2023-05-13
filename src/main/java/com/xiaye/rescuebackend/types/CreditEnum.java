package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CreditEnum {
    INITIAL_CREDIT(100,"初始信用"),
    INCREASE_CREDIT(10,"增加信用"),
    REDUCE_CREDIT(10,"减少信用"),
    SERIOUS_CREDIT(0,"严重失信");
    @EnumValue
    @JsonValue
    private final Integer value;
    private final String description;
}
