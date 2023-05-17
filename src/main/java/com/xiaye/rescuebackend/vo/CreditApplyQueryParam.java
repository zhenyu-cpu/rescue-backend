package com.xiaye.rescuebackend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 信用申请查询参数
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreditApplyQueryParam {
    private PageParam pageParam;
    private Long companyId;
}
