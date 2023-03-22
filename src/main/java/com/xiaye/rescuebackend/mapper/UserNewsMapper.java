package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.UserNews;

/**
* @author 2303015064
* @description 针对表【user_news】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.UserNews
*/
public interface UserNewsMapper extends BaseMapper<UserNews> {

    int deleteByPrimaryKey(Long id);

    int insert(UserNews record);

    int insertSelective(UserNews record);

    UserNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserNews record);

    int updateByPrimaryKey(UserNews record);

}
