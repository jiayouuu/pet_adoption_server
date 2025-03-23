package com.jiayou.pet.exception;

/**
 * 自定义业务异常
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
public class BizException extends RuntimeException {

    private Integer code;

    public BizException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
