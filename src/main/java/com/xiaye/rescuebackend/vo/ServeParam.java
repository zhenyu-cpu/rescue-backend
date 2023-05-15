package com.xiaye.rescuebackend.vo;

import cn.hutool.core.util.ObjectUtil;
import com.xiaye.rescuebackend.model.Serve;
import com.xiaye.rescuebackend.types.ServeStateEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @className: ServeParam
 * @description: 消防服务预约请求体
 * @author: zhenyu
 * @date: 2023/5/14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "消防服务预约请求体")
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
        result.setState(Optional.ofNullable(item.getState()).orElse(ServeStateEnum.RESERVED));
        result.setCreateTime(Optional.ofNullable(item.getCreateTime()).orElse(LocalDateTime.now()));
        if (!ObjectUtil.isNull(item.getRejectMessage()) && ServeStateEnum.REJECTED.equals(item.getState())) {
            result.setRejectMessage(item.getRejectMessage());
        }
        return result;
    }

    public Boolean isInsert() {
        return id == null || id <= 0;
    }
}
