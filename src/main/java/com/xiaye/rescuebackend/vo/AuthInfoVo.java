package com.xiaye.rescuebackend.vo;


import com.xiaye.rescuebackend.model.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
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

    @NotNull
    public static AuthInfoVo toAuthInfoVo(@NotNull User user,@NotNull String token){
        AuthInfoVo result = new AuthInfoVo();
        result.setUsername(user.getUsername());
        result.setPhoneNumber(user.getUserPhone());
        result.setToken(token);
        return result;
    }
}
