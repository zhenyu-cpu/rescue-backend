package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 信用记录表
 * @TableName credit_record
 */
@TableName(value ="credit_record")
@Data
public class CreditRecord implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 变化之前的信用 0-优 1-良 2-差
     */
    @TableField(value = "credit_pre")
    private Long creditPre;

    /**
     * 变化之后的信用
     */
    @TableField(value = "credit_next")
    private Long creditNext;

    /**
     * 信用的变化 0-增加 1-减少
     */
    @TableField(value = "state")
    private Long state;

    /**
     * 根据state来判断是失信事实还是信用增加原因
     */
    @TableField(value = "message")
    private String message;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}