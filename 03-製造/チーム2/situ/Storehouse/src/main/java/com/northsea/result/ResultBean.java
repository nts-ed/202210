package com.northsea.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/14
 * @Description 返回数据统一处理
 */

@Data
@AllArgsConstructor
@ApiModel(value = "全局统一返回结果")
public class ResultBean<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int CHECK_FAIL = 1;
    public static final int NO_PERMISSION = 2;
    public static final int UNKNOWN_EXCEPTION = -99;

    @ApiModelProperty(value = "返回消息")
    private String message = "success";
    @ApiModelProperty(value = "返回码")
    private Integer code = SUCCESS;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.message = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }

    public ResultBean(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBean(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
