package com.ual.blog.web.exception;

import com.ual.blog.common.vo.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author ual
 * 定义Controller层异常处理类
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.fail(ex.getCode(), ex.getMessage());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> allErrors = ex.getAllErrors();
            return Result.fail(400, allErrors.get(0).getDefaultMessage());
        }
        return Result.fail(500, e.getMessage());
    }

}
