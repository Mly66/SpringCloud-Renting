package com.nbmly.renting.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> RestResponse<T> error() {
        RestResponse<T> response = new RestResponse<T>();
        response.setMsg("失败");
        response.setCode(0);
        response.setData(null);
        return response;
    }

    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> response = new RestResponse<T>();
        response.setMsg("请求成功");
        response.setCode(200);
        response.setData(data);
        return response;
    }

    public RestResponse() {
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
