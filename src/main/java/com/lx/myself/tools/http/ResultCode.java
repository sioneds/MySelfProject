package com.lx.myself.tools.http;

public enum ResultCode {
    SUCCESS(200,"成功"),
    ERROR_PASSWORD(201,"密码错误"),
    NOTFIND_USER(202,"用户不存在"),
    STATE_ABNORMAL(203,"账户状态异常"),
    REDIRECT_EXCEPTION(204,"重定向异常"),
    NETWORK_ANOMALY(400,"网络异常"),
    FILE_EXCEPTION(501,"文件处理出现异常");
//    NO_PERMISSION(211,"权限不足"),
//    SERVER_ERROR(10000,"服务器异常"),
//    AUTH_ERROR(10001,"认证失败"),
//    PARAMS_ERROR(10002,"参数错误"),
//    JSON_PARSE_ERROR(10003,"Json解析错误"),
//    ILLEAGAL_STRING(15001,"非法字符串"),
//    UNKNOW_ERROR(16000,"未知错误");


    private int code;
    private String msg;

    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
