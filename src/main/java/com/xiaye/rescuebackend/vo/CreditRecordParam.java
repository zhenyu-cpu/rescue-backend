package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.CreditRecord;
import com.xiaye.rescuebackend.types.CreditRecordStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: CreditRecordParam
 * @description: 信用记录请求参数
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRecordParam {
    private Long id;

    private Long companyId;

    private Long creditPre;

    private Long creditNext;

    private CreditRecordStateEnum state;
    private String message;

    public static CreditRecord convertToCreditRecord(CreditRecordParam item) {
        if (item == null) {
            return null;
        }
        CreditRecord result = new CreditRecord();
        result.setId(item.getId());
        result.setCompanyId(item.getCompanyId());
        result.setCreditPre(item.getCreditPre());
        result.setCreditNext(item.getCreditNext());
        result.setState(item.getState());
        result.setMessage(item.getMessage());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
