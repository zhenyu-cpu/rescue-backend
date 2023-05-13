package com.xiaye.rescuebackend.vo;


import com.xiaye.rescuebackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfoVo implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户手机号，这里需要做脱敏处理
     */
    private String phoneNumber;

    /**
     * 需要的token
     */
    private String token;


    public static AuthInfoVo toAuthInfoVo(User user, String token){
        AuthInfoVo result = new AuthInfoVo();
        result.setUsername(user.getUsername());
        result.setPhoneNumber(user.getUserPhone());
        result.setToken(token);
        return result;
    }
}
