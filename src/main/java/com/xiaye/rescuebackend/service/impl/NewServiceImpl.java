package com.xiaye.rescuebackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.NewsMapper;
import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.model.UserNews;
import com.xiaye.rescuebackend.service.NewsReadService;
import com.xiaye.rescuebackend.service.NewsService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.NewsTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class NewServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    private final UserService userService;
    private final NewsReadService newsReadService;

    public NewServiceImpl(UserService userService, NewsReadService newsReadService) {
        this.userService = userService;
        this.newsReadService = newsReadService;
    }

    @Override
    public Page<News> pageByUserIdForCompanyUser(@NotNull Long userId) {
        return baseMapper.selectPageByUserId(userId);
    }

    @Override
    @Transactional
    public Boolean createNewsByRoles(News news) {
        Long userId = StpUtil.getLoginIdAsLong();
        Long companyId = userService.getById(userId).getCompanyId();
        if (Objects.isNull(news.getCompanyId())) {
            news.setCompanyId(companyId);
        }
        //保存数据
        this.save(news);
        List<User> userList;
        if (news.getType().equals(NewsTypeEnum.FIRE_NEWS)) {
            userList = userService.list();
        } else {
            userList = userService.getUserByCompanyId(companyId);
        }
        List<UserNews> userNewsList = Collections.emptyList();
        for (User user :
                userList) {
            UserNews userNews = new UserNews();
            userNews.setUserId(user.getId());
            userNews.setNewsId(news.getId());
            userNews.setReadFlag(0); // 标记为未读
            userNewsList.add(userNews);
        }

        return newsReadService.saveOrUpdateBatch(userNewsList);
    }
}
