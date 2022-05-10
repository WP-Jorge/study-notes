package com.team001.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局结果封装
 */
@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(0, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return Result.fail(-1, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return Result.fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
