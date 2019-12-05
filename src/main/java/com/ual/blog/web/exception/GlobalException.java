package com.ual.blog.web.exception;

/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author ual
 */
public class GlobalException extends RuntimeException{
    private int code;
    private String message;

    public GlobalException(int code,String message){
        super(message);
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
