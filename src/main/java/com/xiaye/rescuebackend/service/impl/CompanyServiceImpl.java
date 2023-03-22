package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.CompanyMapper;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>implements CompanyService {
}
