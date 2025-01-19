package com.nbmly.renting.common;

import com.nbmly.renting.ErrorCode;

/**
 * 前三位:服务标识
 * 中间两位:模块标识
 * 后两位:异常标识
 * 房管服务异常编码 以163开始
 */
public enum HousingErrorCode implements ErrorCode {
    E_1630101(1630101, "上传房源失败"),
    E_1630102(1630102, "修改房源失败"),
    E_1630201(1630201, "文件上传失败"),
    E_1630202(1630202, "文件删除失败"),
    E_1630301(1630301, "修改房源成功"),
    E_1630302(1630302, "删除房源成功"),
    E_1630401(1630401, "查询房源失败"),
    E_1630501(1630501, "非法用户访问");

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

    private HousingErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static HousingErrorCode setErrorCode(int code) {
        for (HousingErrorCode errorCode : HousingErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
