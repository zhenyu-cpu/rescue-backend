package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.exception.ParamExceptions;
import com.xiaye.rescuebackend.mapper.CompanyMapper;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public Company selectByCompanyCode(String companyCode) throws ParamExceptions {
        if (StrUtil.isEmpty(companyCode)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return baseMapper.selectByCode(companyCode);
    }


    @Override
    public Boolean exitCompanyByCompanyCode(String companyCode) {
        if (StrUtil.isEmpty(companyCode)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return this.selectByCompanyCode(companyCode) != null;
    }

    @Override
    public Page<Company> pageCertifiedCompany(@NotNull Page<Company> page) {
        return baseMapper.selectPageCertifiedCompany(page);
    }
}
