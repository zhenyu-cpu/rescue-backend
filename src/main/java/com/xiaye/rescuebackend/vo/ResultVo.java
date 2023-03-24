package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.types.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 返回类型包装类
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {
    /**
     * 返回的代码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 默认传入值，code和message以及data
     * @param data 需要返回的数据
     */
    public ResultVo(T data){
        this.code = 0;
        this.message = "";
        this.data = data;
    }

    /**
     * 常见成功返回消息封装类
     * @param data 需要返回的数据
     * @param <T> 数据的类型
     * @return 封装后的结果
     */
    public static <T> ResultVo<T> success (T data){
        return new ResultVo<>(ResultCodeEnum.SUCCEED.code(), ResultCodeEnum.SUCCEED.message(), data);
    }

    /**
     * 常见错误信息返回封装类型
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(T data) {
        return new ResultVo<>(ResultCodeEnum.ERROR.code(), ResultCodeEnum.ERROR.message(), data);
    }

    public ResultVo<T> of(ResultCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.code();
        this.message = resultCodeEnum.message();
        return this;
    }
}
