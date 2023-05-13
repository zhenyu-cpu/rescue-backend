package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.types.UserApprovedEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     *
     */
    @TableField(value = "username")
    private String username;
    /**
     *
     */
    @TableField(value = "user_phone")
    private String userPhone;
    /**
     *
     */
    @TableField(value = "password")
    private String password;
    /**
     *
     */
    @TableField(value = "company_id")
    private Long companyId;
    /**
     * 角色类型:
     * system_admin
     * company_admin
     * company_user
     */
    @TableField(value = "role")
    private RoleNameEnum role;
    /**
     * 是否审批
     */
    @TableField(value = "approved")
    private UserApprovedEnum approved;
}