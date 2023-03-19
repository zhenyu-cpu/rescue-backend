package com.xiaye.rescuebackend.mapper;

import com.xiaye.rescuebackend.model.News;

/**
* @author zhenyu
* @description 针对表【news(行政信息)】的数据库操作Mapper
* @createDate 2023-03-19 21:48:36
* @Entity com.xiaye.rescuebackend.model.News
*/
public interface NewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

}
