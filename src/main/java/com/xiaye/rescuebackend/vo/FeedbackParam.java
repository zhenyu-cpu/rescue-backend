package com.xiaye.rescuebackend.vo;

import cn.hutool.core.date.DateUtil;
import com.xiaye.rescuebackend.model.Feedback;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * 反馈信息参数
 */
@Data
@NoArgsConstructor
public class FeedbackParam {

    private Long id;

    @NotBlank
    private String message;
    @NotBlank
    private String title;

    public static FeedbackParam of(Feedback item) {
        FeedbackParam result = new FeedbackParam();
        Optional.ofNullable(item.getId()).ifPresent(result::setId);
        Optional.ofNullable(item.getMessage()).ifPresent(result::setMessage);
        Optional.ofNullable(item.getTitle()).ifPresent(result::setTitle);
        return result;
    }

    /**
     * 将参数，转换为实体类型
     *
     * @param item 传入的参数
     * @return 实体类型
     */
    public static Feedback to(FeedbackParam item) {
        Feedback result = new Feedback();
        Optional.ofNullable(item.getId()).ifPresent(result::setId);
        Optional.ofNullable(item.getMessage()).ifPresent(result::setMessage);
        Optional.ofNullable(item.getTitle()).ifPresent(result::setTitle);
        result.setCreateTime(DateUtil.date().toLocalDateTime());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
