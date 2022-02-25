/**
 * author     : dujun
 * date       : 2021/11/22 13:07
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO;

import lombok.Data;

@Data
public class Result<T> {
    private String code; //状态码 0正常 1异常
    private String msg; //消息
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public Result() {

    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("操作成功");
        return result;
    }
    public static Result success(String msg) {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> success(String msg,T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg(msg);
        return result;
    }


    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("操作成功");
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode("1");
        result.setMsg(msg);
        return result;
    }

}
