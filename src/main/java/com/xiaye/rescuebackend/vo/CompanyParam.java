package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: CompanyParam
 * @description: TODO 公司参数
 * @author: zhenyu
 * @date: 2023/4/8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "创建公司请求体")
public class CompanyParam {
    //公司id
    private Long id;
    //公司名
    private String companyName;
    //公司社会统一新信用号码
    private String code;

    //公司信用程度
    private Long credit;
    //公司所在地址
    private String address;

    public static Company convertToCompany(CompanyParam item) {
        if (item == null) {
            return null;
        }
        Company result = new Company();
        result.setId(item.getId());
        result.setName(item.getCompanyName());
        result.setCode(item.getCode());
        result.setCredit(item.getCredit());
        result.setAddress(item.getAddress());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
