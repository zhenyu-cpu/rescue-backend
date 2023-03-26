package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.Company;

/**
* @author 2303015064
* @description 针对表【company(公司表)】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.Company
*/
public interface CompanyMapper extends BaseMapper<Company> {

    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    Company selectByCode(String code);
}
