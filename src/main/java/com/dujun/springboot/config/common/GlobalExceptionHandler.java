/*
 * author     : dujun
 * date       : 2022/7/13 15:27
 * description: 全局异常处理
 */

package com.dujun.springboot.config.common;

import com.dujun.springboot.VO.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public Result<?> exception(NullPointerException nullPointerException){
        nullPointerException.printStackTrace();
        return Result.error("空指针"+nullPointerException);
    }

}
