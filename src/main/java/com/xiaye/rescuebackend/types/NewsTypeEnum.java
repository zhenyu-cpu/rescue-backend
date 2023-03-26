package com.xiaye.rescuebackend.types;

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
    private String newsTypeName;
    /**
     * 消息类型描述
     */
    private String description;
}
