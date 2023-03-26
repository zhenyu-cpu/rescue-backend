package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.NewsMapper;
import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
}
