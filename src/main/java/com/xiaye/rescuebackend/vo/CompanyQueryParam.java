package com.xiaye.rescuebackend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公司查询参数
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyQueryParam {
    private PageParam pageParam;
    private Boolean enableAll;
}
