package com.nbmly.renting.common;

import com.nbmly.renting.ErrorCode;

/**
 * 前三位:服务标识
 * 中间两位:模块标识
 * 后两位:异常标识
 * 账户服务异常编码 以162开始
 */
public enum AccountErrorCode implements ErrorCode {

    E_130101(1610101, "注册失败"),
    E_130111(1610111, "用户名重复"),
    E_130103(1610103, "修改失败");

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

    private AccountErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccountErrorCode setErrorCode(int code) {
        for (AccountErrorCode errorCode : AccountErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
