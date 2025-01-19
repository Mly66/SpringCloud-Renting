package com.nbmly.renting;

/**
 * 异常编码 0成功、-1熔断、 -2 标准参数校验不通过 -3会话超时
 * 前两位:服务标识
 * 中间两位:模块标识
 * 后两位:异常标识
 */
public enum CommonErrorCode implements ErrorCode {

    //////////////////////////////////// 公用异常编码 //////////////////////////

    SUCCESS(0, "成功"),
    CUSTOM(999998, "自定义异常"),
    /**
     * 未知错误
     */
    UNKOWN(999999, "未知错误");

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

    private CommonErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommonErrorCode setErrorCode(int code) {
        for (CommonErrorCode errorCode : CommonErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
