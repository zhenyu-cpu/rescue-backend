package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.mapper.UserMapper;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    public User selectUserByPhoneNumberAndApproved(String phoneNumber){
        if(ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        if(ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public Boolean exitUserByPhoneNumber(String phoneNumber) {
        if (ObjectUtil.isEmpty(phoneNumber)){
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_VERIFY_ERROR);
        }
        return selectUserByPhoneNumber(phoneNumber) != null ? true : false;
    }
}
