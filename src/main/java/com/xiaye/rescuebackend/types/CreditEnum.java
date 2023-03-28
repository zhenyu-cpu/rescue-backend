package com.xiaye.rescuebackend.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public enum CreditEnum {
    INITIAL_CREDIT(100,"初始信用"),
    INCREASE_CREDIT(10,"增加信用"),
    REDUCE_CREDIT(10,"减少信用"),
    SERIOUS_CREDIT(0,"严重失信");
    private final Integer value;
    private final String description;
}
