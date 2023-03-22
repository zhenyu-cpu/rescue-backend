package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.UserMapper;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    public User selectUserByPhoneNumber(String phoneNumber){
        if(ObjectUtil.isEmpty(phoneNumber)){
            return null;
        }
        return baseMapper.selectByUser_phone(phoneNumber);
    }
}
