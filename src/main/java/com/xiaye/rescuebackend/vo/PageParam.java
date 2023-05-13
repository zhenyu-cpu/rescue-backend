package com.xiaye.rescuebackend.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {
    @NotNull
    private Integer currentPage;
    @NotNull
    private Integer pageSize;

    public static PageParam of(Page item) {
        PageParam result = new PageParam();
        result.setCurrentPage(Math.toIntExact(item.getCurrent()));
        result.setPageSize(Math.toIntExact(item.getSize()));
        return result;
    }

    public static <T> Page<T> to(PageParam item) {
        Page<T> result = new Page<>();
        result.setCurrent(item.getCurrentPage());
        result.setSize(item.getPageSize());
        return result;
    }
}
