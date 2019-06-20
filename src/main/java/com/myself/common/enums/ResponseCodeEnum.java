package com.myself.common.enums;

/**
 * @author zhangqiling
 * @date 2019/6/20
 * @description http返回状态码实例
 * @version V1.0
 */
public enum ResponseCodeEnum {
    //成功
    SUCCESS(200),
    //400失败
    FAIL(400),
    //401权限失败
    UNAUTHORIZED(401),
    //404找不到地址
    NOT_FOUND(404),
    //500服务器错误
    INTERNAL_SERVER_ERROR(500);

    /**
     * 实例码
     */
    public int code;

    /**
     * 构造code
     * @param code 返回码
     */
    ResponseCodeEnum(int code) {
        this.code = code;
    }
}
