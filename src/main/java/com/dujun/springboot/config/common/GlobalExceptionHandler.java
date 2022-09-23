/*
 * author     : dujun
 * date       : 2022/7/13 15:27
 * description: 全局异常处理
 */

package com.dujun.springboot.config.common;

import com.dujun.springboot.VO.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public Result<?> nullPointerException(NullPointerException nullPointerException){
        nullPointerException.printStackTrace();
        return Result.error("空指针"+nullPointerException);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> otherException(Exception e){
        e.printStackTrace();
        return Result.error("系统异常啦");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> accessDeniedException(AccessDeniedException e){
        e.printStackTrace();
        return Result.error(String.valueOf(HttpStatus.FORBIDDEN.value()),"用户权限不足");
    }

}
