package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.model.Serve;
import com.xiaye.rescuebackend.types.ServeStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @className: ServeParam
 * @description: 消防服务预约请求体
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServeParam {
    private Long id;

    private Long companyId;

    private String serveLocation;

    private String doUserName;

    private String doUserPhone;

    private ServeStateEnum state;

    private LocalDateTime createTime;

    private String rejectMessage;

    public static Serve convertToServe(ServeParam item) {
        if (item == null) {
            return null;
        }
        Serve result = new Serve();
        result.setId(item.getId());
        result.setCompanyId(item.getCompanyId());
        result.setServeLocation(item.getServeLocation());
        result.setDouserName(item.getDoUserName());
        result.setDouserPhone(item.getDoUserPhone());
        result.setState(item.getState());
        result.setCreateTime(item.getCreateTime());
        result.setRejectMessage(item.getRejectMessage());
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
