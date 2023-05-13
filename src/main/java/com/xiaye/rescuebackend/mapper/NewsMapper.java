package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaye.rescuebackend.model.News;

/**
* @author 2303015064
* @description 针对表【news(行政信息)】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.News
*/
public interface NewsMapper extends BaseMapper<News> {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    Page<News> selectPageByUserId(Long userId);
}
