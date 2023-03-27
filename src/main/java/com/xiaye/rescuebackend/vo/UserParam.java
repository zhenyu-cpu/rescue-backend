package com.xiaye.rescuebackend.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
}
