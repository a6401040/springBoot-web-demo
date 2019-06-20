package com.myself.common;



import com.myself.common.enums.ResponseCodeEnum;
import com.myself.utils.JsonUtil;

/**
 * @author zhangqiling
 * @date 2019/6/20
 * @description 构建json返回http请求
 * @version V1.0
 */
public class ReturnResponse {

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据json值
     */
    private Object data;

    /**
     * Gets the value of code.
     *
     * @return the value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the value of message.
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the value of data.
     *
     * @return the value of data
     */
    public Object getData() {
        return data;
    }

    /**
      * @description responseCodeEnum
      * @param responseCodeEnum 返回码枚举
      * @return com.myself.common.ReturnResponse
      */
    public ReturnResponse setCode(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.code;
        return this;
    }


    /**
      * 设置消息
      * @param message 消息
      * @return com.myself.common.ReturnResponse
      */
    public ReturnResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
      * 设置json数据值
      * @param data json数据值
      * @return com.myself.common.ReturnResponse
      */
    public ReturnResponse setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 重写toString 返回json string
     * @return String
     */
    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}
