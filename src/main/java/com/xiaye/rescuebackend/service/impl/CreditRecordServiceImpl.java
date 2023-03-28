package com.xiaye.rescuebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.mapper.CreditRecordMapper;
import com.xiaye.rescuebackend.model.CreditRecord;
import com.xiaye.rescuebackend.service.CreditRecordService;
import org.springframework.stereotype.Service;

@Service
public class CreditRecordServiceImpl extends ServiceImpl<CreditRecordMapper, CreditRecord> implements CreditRecordService {
}
