package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 公司表
 * @TableName company
 */
@TableName(value ="company")
@Data
public class Company implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 信用码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 公司信用程度
     */
    @TableField(value = "credit")
    private Long credit;

    /**
     * 公司所在地址

     */
    @TableField(value = "address")
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}