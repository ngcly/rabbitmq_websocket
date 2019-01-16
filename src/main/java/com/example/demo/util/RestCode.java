package com.example.demo.util;

/**
 * 统一错误码
 */
public enum RestCode {
    SUCCESS(200, "操作成功"),
    USER_ERR(333,"用户名或密码错误"),
    USER_DISABLE(334,"用户被禁用"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401 ,"未认证"),
    UNAUTHEN(4401, "未登录"),
    UNAUTHZ(301 ,"未授权，拒绝访问"),
    NOT_FOUND(404 ,"未找到"),
    REQUEST_METHOD_NOT_SUPPORTED(405 ,"不支持该请求方式"),
    SERVER_ERROR(500, "服务器内部错误");

    public int code;
    public String msg;

    RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}