package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.types.UserApprovedEnum;
import lombok.Data;

@Data
public class UserInfoVo {
    //用户名
    private String username;
    //手机号
    private String phoneNumber;
    //角色
    private RoleNameEnum role;
    //所属于公司
    private String company;
    //公司的id
    private Long companyId;
    //用户是否通过审批
    private UserApprovedEnum approved;

    public static UserInfoVo of(User user) {
        UserInfoVo item = new UserInfoVo();
        item.setUsername(user.getUsername());
        item.setPhoneNumber(user.getUserPhone());
        item.setRole(user.getRole());
        item.setCompanyId(user.getCompanyId());
        item.setApproved(user.getApproved());
        return item;
    }

    public static UserInfoVo of(User user, Company company) {
        UserInfoVo result = UserInfoVo.of(user);
        result.setCompany(company.getName());
        return result;
    }
}
