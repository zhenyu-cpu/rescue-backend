package com.xiaye.rescuebackend.exception;

import com.xiaye.rescuebackend.types.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception{
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    @NotNull
    @Contract("_ -> new")
    public static BaseException byEnum(@NotNull ResultCodeEnum re){
        return new BaseException(re.code(),re.message());
    }
}
