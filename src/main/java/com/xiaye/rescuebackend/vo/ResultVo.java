package com.xiaye.rescuebackend.vo;

import com.xiaye.rescuebackend.types.ResultCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


/**
 * @author zhenyu
 * @date 2023/4/01
 */
@Data
@Builder
public class ResultVo implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static ResultVo success() {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCodeEnum(ResultCodeEnum.SUCCESS);
        return resultVo;
    }


    public static ResultVo success(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultCodeEnum.SUCCESS.code());
        resultVo.setMsg(message);
        return resultVo;
    }

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCodeEnum(ResultCodeEnum.SUCCESS);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo failure() {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCodeEnum(ResultCodeEnum.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
        return resultVo;
    }

    public static ResultVo failure(ResultCodeEnum resultCode) {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCodeEnum(resultCode);
        return resultVo;
    }

    public static ResultVo failure(ResultCodeEnum resultCode, Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setResultCodeEnum(resultCode);
        resultVo.setData(data);
        return resultVo;
    }

    public void setResultCodeEnum(ResultCodeEnum code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
 
