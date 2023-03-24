package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.CreditRecord;

/**
* @author 2303015064
* @description 针对表【credit_record(信用记录表)】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.CreditRecord
*/
public interface CreditRecordMapper extends BaseMapper<CreditRecord> {

    int deleteByPrimaryKey(Long id);

    int insert(CreditRecord record);

    int insertSelective(CreditRecord record);

    CreditRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditRecord record);

    int updateByPrimaryKey(CreditRecord record);

}
