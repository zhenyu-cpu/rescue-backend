package com.xiaye.rescuebackend.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UpdatePasswordParam {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
