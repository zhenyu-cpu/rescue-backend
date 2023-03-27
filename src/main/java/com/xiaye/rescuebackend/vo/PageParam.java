package com.xiaye.rescuebackend.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class PageParam {
    @javax.validation.constraints.NotNull
    private Integer currentPage;
    @javax.validation.constraints.NotNull
    private Integer pageSize;

    @NotNull
    public static PageParam of(@NotNull Page item) {
        PageParam result = new PageParam();
        result.setCurrentPage(Math.toIntExact(item.getCurrent()));
        result.setPageSize(Math.toIntExact(item.getSize()));
        return result;
    }

    @NotNull
    public static <T> Page<T> to(@NotNull PageParam item) {
        Page<T> result = new Page<>();
        result.setCurrent(item.getCurrentPage());
        result.setSize(item.getPageSize());
        return result;
    }
}
