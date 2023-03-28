package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class UserInfoVo {
    //用户名
    private String username;
    //手机号
    private String phoneNumber;
    //角色
    private String role;
    //所属于公司
    private String company;
    //公司的id
    private Long companyId;
    //用户是否通过审批
    private Integer approved;

    @NotNull
    public static UserInfoVo of(@NotNull User user){
        UserInfoVo item = new UserInfoVo();
        item.setUsername(user.getUsername());
        item.setPhoneNumber(user.getUserPhone());
        item.setRole(user.getRole());
        item.setCompanyId(user.getCompanyId());
        item.setApproved(user.getApproved());
        return item;
    }

    @NotNull
    public static UserInfoVo of(User user, @NotNull Company company){
        UserInfoVo result = UserInfoVo.of(user);
        result.setCompany(company.getName());
        return result;
    }
}
