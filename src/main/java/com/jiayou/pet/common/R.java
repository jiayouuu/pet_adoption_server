package com.jiayou.pet.common;

/**
 * 接口统一返回包装类
 *
 * @author: jiayou
 * @date: 2024-01-08
 */
public class R {

    private String code;
    private String msg;
    private Object data;

    public static R success() {
        return new R(Constants.CODE_200, "", null);
    }

    public static R success(Object data) {
        return new R(Constants.CODE_200, "", data);
    }

    public static R error(String code, String msg) {
        return new R(code, msg, null);
    }

    public static R error() {
        return new R(Constants.CODE_500, "系统错误", null);
    }

    public R() {
    }

    public R(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
