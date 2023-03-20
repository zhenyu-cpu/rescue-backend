package com.xiaye.rescuebackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

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
    private Long company_id;

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
    private String is_readed;

    /**
     * 信息类型（消防信息。行政信息）
     */
    @TableField(value = "type")
    private Long type;

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
        News other = (News) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompany_id() == null ? other.getCompany_id() == null : this.getCompany_id().equals(other.getCompany_id()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getIs_readed() == null ? other.getIs_readed() == null : this.getIs_readed().equals(other.getIs_readed()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompany_id() == null) ? 0 : getCompany_id().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getIs_readed() == null) ? 0 : getIs_readed().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company_id=").append(company_id);
        sb.append(", title=").append(title);
        sb.append(", context=").append(context);
        sb.append(", date=").append(date);
        sb.append(", is_readed=").append(is_readed);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}