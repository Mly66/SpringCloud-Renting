package com.nbmly.renting.common;

import com.nbmly.renting.ErrorCode;

/**
 * 前三位:服务标识
 * 中间两位:模块标识
 * 后两位:异常标识
 * 权限服务异常编码 以162开始
 */
public enum UserErrorCode implements ErrorCode {

    E_130101(1620101, "权限注册失败");

    private int code;
    private String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    private UserErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserErrorCode setErrorCode(int code) {
        for (UserErrorCode errorCode : UserErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
