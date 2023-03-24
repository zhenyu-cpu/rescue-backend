package com.xiaye.rescuebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaye.rescuebackend.model.Serve;

/**
* @author 2303015064
* @description 针对表【serve(消防服务预约表)】的数据库操作Mapper
* @createDate 2023-03-22 14:49:40
* @Entity com.xiaye.rescuebackend.model.Serve
*/
public interface ServeMapper extends BaseMapper<Serve> {

    int deleteByPrimaryKey(Long id);

    int insert(Serve record);

    int insertSelective(Serve record);

    Serve selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Serve record);

    int updateByPrimaryKey(Serve record);

}
