package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_news
 */
@TableName(value ="user_news")
@Data
public class UserNews implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "news_id")
    private Long newsId;

    /**
     * 新闻已读标记
     */
    @TableField(value = "read_flag")
    private Integer readFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}