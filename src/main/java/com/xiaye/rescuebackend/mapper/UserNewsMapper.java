package com.xiaye.rescuebackend.mapper;

import com.xiaye.rescuebackend.model.UserNews;

/**
* @author zhenyu
* @description 针对表【user_news】的数据库操作Mapper
* @createDate 2023-03-21 23:16:42
* @Entity com.xiaye.rescuebackend.model.UserNews
*/
public interface UserNewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserNews record);

    int insertSelective(UserNews record);

    UserNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserNews record);

    int updateByPrimaryKey(UserNews record);

}
