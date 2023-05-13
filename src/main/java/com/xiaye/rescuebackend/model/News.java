package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaye.rescuebackend.types.NewsTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行政信息
 * @TableName news
 */
@TableName(value ="news")
@Data
public class News implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 公司id

     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 正文
     */
    @TableField(value = "context")
    private String context;

    /**
     * 
     */
    @TableField(value = "date")
    private LocalDateTime date;

    /**
     * 已经读过信息的人
     */
    @TableField(value = "is_readed")
    private String isReaded;

    /**
     * 信息类型（消防信息。行政信息）
     */
    @TableField(value = "type")
    private NewsTypeEnum type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}