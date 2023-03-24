package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 信用申请表
 * @TableName credit_apply
 */
@TableName(value ="credit_apply")
@Data
public class CreditApply implements Serializable {
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
     * 
     */
    @TableField(value = "applyuser_name")
    private String applyuserName;

    /**
     * 
     */
    @TableField(value = "applyuser_phone")
    private String applyuserPhone;

    /**
     * 时间由用户传递
     */
    @TableField(value = "apply_time")
    private LocalDateTime applyTime;

    /**
     * 失信处事实及处罚
     */
    @TableField(value = "message")
    private String message;

    /**
     * 整改情况
     */
    @TableField(value = "change_message")
    private String changeMessage;

    /**
     * 信用承诺
     */
    @TableField(value = "promise_message")
    private String promiseMessage;

    /**
     * 状态 0-已申请 1-已驳回 2-已通过
     */
    @TableField(value = "state")
    private Long state;

    /**
     * 当状态为1-已驳回时才需要驳回原因
     */
    @TableField(value = "reject_message")
    private String rejectMessage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}