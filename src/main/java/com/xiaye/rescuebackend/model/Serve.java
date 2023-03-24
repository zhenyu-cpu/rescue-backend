package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 消防服务预约表
 * @TableName serve
 */
@TableName(value ="serve")
@Data
public class Serve implements Serializable {
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
    @TableField(value = "serve_location")
    private String serveLocation;

    /**
     * 
     */
    @TableField(value = "douser_name")
    private String douserName;

    /**
     * 
     */
    @TableField(value = "douser_phone")
    private String douserPhone;

    /**
     * 0-已预约 1-已通过 2-已拒绝 3-已失信 4-已完成
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 时间由系统生成
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 拒绝原因，被拒绝状态才有
     */
    @TableField(value = "reject_message")
    private String rejectMessage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}