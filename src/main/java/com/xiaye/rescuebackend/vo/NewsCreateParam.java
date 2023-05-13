package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.types.NewsTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @className: NewsCreateParam
 * @description: 新闻创建请求体
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCreateParam {
    private String title;
    private String context;
    private LocalDateTime date;

    private NewsTypeEnum type;

    public static News convertToNews(NewsCreateParam item) {
        if (item == null) {
            return null;
        }
        News result = new News();
        result.setTitle(item.getTitle());
        result.setContext(item.getContext());
        result.setDate(item.getDate());
        result.setType(item.getType());
        return result;
    }
}
