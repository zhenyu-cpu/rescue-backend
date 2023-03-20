package com.xiaye.rescuebackend.mapper;

import com.xiaye.rescuebackend.model.User;

/**
* @author zhenyu
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-20 22:49:43
* @Entity com.xiaye.rescuebackend.model.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
