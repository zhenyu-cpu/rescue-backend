package com.xiaye.rescuebackend.vo;

import lombok.Data;

/**
 * 用户查询条件
 */
@Data
public class UserQueryParam {
    //公司id
    private Long companyId;

    //是否通过审核
    private Boolean isPass;

    //角色类型
    private String roleType;
}
