package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
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
system_admin
company_admin
company_user
     */
    @TableField(value = "role")
    private String role;

    /**
     * 是否审批
     */
    @TableField(value = "approved")
    private Integer approved;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}