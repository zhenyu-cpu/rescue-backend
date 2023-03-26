package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.User;
import lombok.Data;

@Data
public class UserVo {
    //用户名
    private String username;
    //手机号
    private String phoneNumber;
    //角色
    private String role;
    //所属于公司
    private String company;

    private UserVo of(User user){
        UserVo item = new UserVo();
        item.setUsername(user.getUsername());
        item.setPhoneNumber(user.getUserPhone());
        item.setRole(user.getRole());
        return item;
    }
}
