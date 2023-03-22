package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.NewsMapper;
import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class NewServiceImpl extends ServiceImpl<NewsMapper,News> implements NewsService {
}
