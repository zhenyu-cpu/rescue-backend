package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.User;

/**
* @author 2303015064
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.User
*/
public interface UserMapper extends BaseMapper<User> {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 通过用户的电话号码，查询用户
     * @param phoneNumber 手机号码
     * @return 查询到的用户
     */
    User selectByUser_phone(String phoneNumber);
}
