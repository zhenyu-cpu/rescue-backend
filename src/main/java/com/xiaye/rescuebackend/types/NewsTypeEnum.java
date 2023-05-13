package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NewsTypeEnum {
    FIRE_NEWS("fire_news","消防消息"),
    ADMINISTRATIVE_NEWS("administrative_news","行政消息");

    /**
     * 消息类型名称
     */
    @EnumValue
    @JsonValue
    private final String newsTypeName;
    /**
     * 消息类型描述
     */
    private final String description;
}
