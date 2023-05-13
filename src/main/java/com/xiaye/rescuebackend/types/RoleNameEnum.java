package com.xiaye.rescuebackend.types;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleNameEnum {
    SYSTEM_ADMIN("system_admin", "系统管理员"),
    COMPANY_ADMIN("company_admin", "公司管理员"),
    COMPANY_USER("company_user", "公司用户");

    public static final String SYSTEM_ADMIN_ROLE = "system_admin";
    public static final String COMPANY_ADMIN_ROLE = "company_admin";
    public static final String COMPANY_USER_ROLE = "company_user";
    @EnumValue
    @JsonValue
    private final String roleName;
    private final String description;
}
