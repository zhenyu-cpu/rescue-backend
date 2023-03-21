package com.xiaye.rescuebackend.mapper;

import com.xiaye.rescuebackend.model.Company;

/**
* @author zhenyu
* @description 针对表【company(公司表)】的数据库操作Mapper
* @createDate 2023-03-21 23:16:42
* @Entity com.xiaye.rescuebackend.model.Company
*/
public interface CompanyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

}
