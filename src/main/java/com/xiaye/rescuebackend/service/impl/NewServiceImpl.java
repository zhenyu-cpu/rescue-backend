package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.News;
import com.xiaye.rescuebackend.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class NewServiceImpl implements NewsService {
    @Override
    public boolean saveBatch(Collection<News> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<News> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<News> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(News entity) {
        return false;
    }

    @Override
    public News getOne(Wrapper<News> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<News> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<News> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<News> getBaseMapper() {
        return null;
    }

    @Override
    public Class<News> getEntityClass() {
        return null;
    }
}
