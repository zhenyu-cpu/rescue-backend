package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

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

    @NotNull
    public static UserVo of(@NotNull User user){
        UserVo item = new UserVo();
        item.setUsername(user.getUsername());
        item.setPhoneNumber(user.getUserPhone());
        item.setRole(user.getRole());
        return item;
    }

    @NotNull
    public static UserVo of(User user, @NotNull Company company){
        UserVo result = UserVo.of(user);
        result.setCompany(company.getName());
        return result;
    }
}
