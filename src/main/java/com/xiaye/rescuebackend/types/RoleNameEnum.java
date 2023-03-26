package com.xiaye.rescuebackend.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleNameEnum {
    SYSTEM_ADMIN("system_admin", "系统管理员"),
    COMPANY_ADMIN("company_admin", "公司管理员"),
    COMPANY_USER("company_user", "公司用户");
    private String roleName;
    private String description;
}
