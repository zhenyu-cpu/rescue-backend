package com.xiaye.rescuebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaye.rescuebackend.model.Company;

/**
 * 公司服务
 */
public interface CompanyService extends IService<Company> {
    //通过公司查询公司，因为公司信用号码每个公司保持唯一

    /**
     * 通过公司信用号码去查询公司
     * @param companyCode 公司信用码
     * @return 公司实体类型
     */
    Company selectByCompanyCode(String companyCode);

    /**
     * 通过公司信用号码去查询公司是否以及注册
     *
     * @param companyCode 公司实体码
     * @return 是否存在
     */
    Boolean exitCompanyByCompanyCode(String companyCode);

    Page<Company> pageCertifiedCompany(Page<Company> page);
}
