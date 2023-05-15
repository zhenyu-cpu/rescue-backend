package com.xiaye.rescuebackend.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.model.UserNews;
import com.xiaye.rescuebackend.service.NewsReadService;
import com.xiaye.rescuebackend.service.NewsService;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.vo.NewsCreateParam;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news")
@Tag(name = "新闻管理")
public class NewsController {

    private final NewsService newsService;
    private final NewsReadService newsReadService;

    @Autowired
    public NewsController(NewsService newsService, NewsReadService newsReadService) {
        this.newsService = newsService;
        this.newsReadService = newsReadService;
    }

    @GetMapping("/list")
    @Operation(summary = "获取新闻列表")
    public ResultVo list(@RequestBody @Validated PageParam pageParam) {
        Page<News> page = PageParam.to(pageParam);
        //从登陆用户获取不同的用户角色
        //如果他拥有系统管理员,就获取数据表中所有的新闻列表
        if (StpUtil.hasRole(RoleNameEnum.SYSTEM_ADMIN_ROLE)) {
            Page<News> newsPage = newsService.page(page);
            return ResultVo.success(newsPage);
        }

        //如果他不是系统管理员，就获取该数据表中本公司的消防信息和行政信息
        Long userId = StpUtil.getLoginIdAsLong();
        Page<News> newsPage = newsService.pageByUserIdForCompanyUser(userId);
        return ResultVo.success(newsPage);
    }

    @GetMapping("/get")
    @Operation(summary = "获取新闻详情")
    public ResultVo getNews(@RequestParam(name = "id", required = true) @NotNull Long id) {
        return ResultVo.success(newsService.getById(id));
    }

    @GetMapping("/create")
    @Operation(summary = "创建新闻")
    @SaCheckRole(value = {RoleNameEnum.SYSTEM_ADMIN_ROLE, RoleNameEnum.COMPANY_ADMIN_ROLE}, mode = SaMode.OR)
    public ResultVo createNews(@RequestBody @Validated NewsCreateParam newsCreateParam) {
        if (newsService.createNewsByRoles(NewsCreateParam.convertToNews(newsCreateParam))) {
            return ResultVo.success();
        }
        return ResultVo.failure();
    }


    @PutMapping("/read/all")
    @Operation(summary = "阅读用户所有未读新闻")
    public ResultVo readAllNews() {
        Long userId = StpUtil.getLoginIdAsLong();
        LambdaQueryChainWrapper<UserNews> lambdaQueryChainWrapper = newsReadService.lambdaQuery();
        UpdateChainWrapper<UserNews> updateWrapper = newsReadService.update();
        updateWrapper.set("read_flag", 1)
                .eq("user_id", userId)
                .eq("read_flag", 0);
        if (updateWrapper.update()) {
            return ResultVo.success("新闻一键阅读完成");
        }
        return ResultVo.failure();
    }

    @PutMapping("/read")
    @Operation(summary = "阅读某个新闻")
    public ResultVo readNews(@RequestParam @NotNull Long newsId) {
        Long userId = StpUtil.getLoginIdAsLong();
        UpdateChainWrapper<UserNews> updateChainWrapper = newsReadService.update();
        updateChainWrapper.set("read_flag", 1).eq("user_id", userId).eq("news_id", newsId)
                .eq("read_flag", 0);
        if (updateChainWrapper.update()) {
            return ResultVo.success("新闻阅读完成");
        }
        return ResultVo.failure();
    }

    @DeleteMapping(value = {"/del", "/delete"})
    @Operation(summary = "删除某个新闻")
    public ResultVo deleteNews(@RequestParam(name = "id", required = true) Long id) {
        //删除失败
        if (!newsService.removeById(id)) {
            return ResultVo.failure();
        }

        if (!newsReadService.removeById(id)) {
            return ResultVo.failure();
        }
        return ResultVo.success();
    }

    @GetMapping(value = {"/unread"})
    @Operation(summary = "获取未读取的新闻")
    public ResultVo unreadNews(@RequestBody @Validated PageParam pageParam) {
        Long userId = StpUtil.getLoginIdAsLong();

        LambdaQueryChainWrapper<UserNews> queryChainWrapper = newsReadService.lambdaQuery();
        queryChainWrapper.eq(UserNews::getUserId, userId);
        List<UserNews> userNewsList = queryChainWrapper.list();
        List<Long> newsIdList = userNewsList.stream().map(UserNews::getNewsId).distinct().collect(Collectors.toList());
        LambdaQueryChainWrapper<News> newsLambdaQueryChainWrapper = newsService.lambdaQuery();
        newsLambdaQueryChainWrapper.in(News::getId, newsIdList);
        return ResultVo.success(newsLambdaQueryChainWrapper.page(PageParam.to(pageParam)));
    }
}
