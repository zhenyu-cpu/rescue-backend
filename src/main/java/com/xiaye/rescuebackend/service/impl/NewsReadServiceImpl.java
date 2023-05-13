package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.UserNewsMapper;
import com.xiaye.rescuebackend.model.UserNews;
import com.xiaye.rescuebackend.service.NewsReadService;
import org.springframework.stereotype.Service;

/**
 * @className: NewsReadServiceImpl
 * @description: TODO 类描述
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Service
public class NewsReadServiceImpl extends ServiceImpl<UserNewsMapper, UserNews> implements NewsReadService {
}
