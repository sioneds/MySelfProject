package com.lx.myself.tools.http;


import java.io.Serializable;

import static com.lx.myself.tools.http.ErrorCode.SUCCESS;

/**
 * ResponseData
 *
 * @author Administrator
 * @date 2021/03/29 18:27
 **/
public class ResponseData implements Serializable {

    private static final long serialVersionUID = -6936648847780505144L;

    /**
     * 状态码
     */
    public Integer code;

    /**
     * 返回的消息
     */
    public String message;

    /**
     * 返回的数据
     */
    public Object data;

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResponseData success() {
        return new ResponseData(200);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(200,null,data);
    }

    public static ResponseData error(ErrorCode errorCode) {
        return new ResponseData(errorCode.getCode(),errorCode.getMsg(),null);
    }

    public ResponseData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseData(Integer code) {
        this.code = code;
    }
    public ResponseData() {
    }
}