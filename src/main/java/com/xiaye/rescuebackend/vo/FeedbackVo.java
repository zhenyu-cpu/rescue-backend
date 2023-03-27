package com.xiaye.rescuebackend.vo;

import cn.hutool.core.date.DateUtil;
import com.xiaye.rescuebackend.model.Feedback;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
public class FeedbackVo {
    private Long id;

    private String message;

    private String title;
    private Date createTime;

    @NotNull
    public static FeedbackVo of(@NotNull Feedback item){
        FeedbackVo result = new FeedbackVo();
        result.setId(item.getId());
        result.setMessage(item.getMessage());
        result.setTitle(item.getTitle());
        result.setCreateTime(DateUtil.date(item.getCreateTime()));
        return result;
    }
}
