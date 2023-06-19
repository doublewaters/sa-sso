package com.github.doublewaters.ssoserver.exception;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhaowenqi
 * @version 1.0.0
 * @date 2023年05月26日 09:25:03
 * @packageName com.github.doublewaters.ssoserver.exception
 * @className GlobalExceptionHandler
 * @describe TODO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}
