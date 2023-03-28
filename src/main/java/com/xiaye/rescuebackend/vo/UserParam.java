package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@NoArgsConstructor
public class UserParam {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String phoneNumber;

    @javax.validation.constraints.NotNull
    private Long companyId;

    public Boolean isInsert(){
        return id == null;
    }

    public static User to(UserParam item){
        User result = new User();
        Optional.ofNullable(item.getId()).ifPresent(result::setId);
        Optional.ofNullable(item.getUsername()).ifPresent(result::setUsername);
        Optional.ofNullable(item.getPhoneNumber()).ifPresent(result::setUserPhone);
        Optional.of(item.getCompanyId()).ifPresent(result::setCompanyId);
        return result;
    }
}
