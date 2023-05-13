package com.xiaye.rescuebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaye.rescuebackend.model.News;

import javax.validation.constraints.NotNull;

/**
 * 新闻服务
 */
public interface NewsService extends IService<News> {

    Page<News> pageByUserIdForCompanyUser(@NotNull Long userId);

    Boolean createNewsByRoles(News news);
}
