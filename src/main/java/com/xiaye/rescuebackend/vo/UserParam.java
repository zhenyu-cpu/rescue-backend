package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserParam {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String phoneNumber;

    @NotNull
    private Long companyId;

    public static User to(UserParam item) {
        User result = new User();
        result.setId(item.getId());
        result.setUsername(item.getUsername());
        result.setUserPhone(item.getPhoneNumber());
        result.setCompanyId(item.getCompanyId());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
