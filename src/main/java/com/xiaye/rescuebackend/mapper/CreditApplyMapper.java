package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.CreditApply;

/**
* @author 2303015064
* @description 针对表【credit_apply(信用申请表)】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.CreditApply
*/
public interface CreditApplyMapper extends BaseMapper<CreditApply> {

    int deleteByPrimaryKey(Long id);

    int insert(CreditApply record);

    int insertSelective(CreditApply record);

    CreditApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditApply record);

    int updateByPrimaryKey(CreditApply record);

}
