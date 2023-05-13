package com.xiaye.rescuebackend.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "认证参数", description = "用于用户身份认证的参数")
public class AuthParam {
    /**
     * 手机号码
     */
    @NotBlank
    @Schema(description = "用户手机号码", required = true)
    private String phoneNumber;

    /**
     * 密码
     */
    @NotBlank
    @Schema(description = "用户密码", required = true)
    private String password;
}
