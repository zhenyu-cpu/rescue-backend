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
    private Long user_id;

    /**
     * 
     */
    @TableField(value = "news_id")
    private Long news_id;

    /**
     * 新闻已读标记
     */
    @TableField(value = "read_flag")
    private Integer read_flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserNews other = (UserNews) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getNews_id() == null ? other.getNews_id() == null : this.getNews_id().equals(other.getNews_id()))
            && (this.getRead_flag() == null ? other.getRead_flag() == null : this.getRead_flag().equals(other.getRead_flag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getNews_id() == null) ? 0 : getNews_id().hashCode());
        result = prime * result + ((getRead_flag() == null) ? 0 : getRead_flag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", user_id=" + user_id +
                ", news_id=" + news_id +
                ", read_flag=" + read_flag +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}