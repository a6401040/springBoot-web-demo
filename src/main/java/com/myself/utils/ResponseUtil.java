package com.myself.utils;

import com.myself.common.CommonConstant;
import com.myself.common.ReturnResponse;
import com.myself.common.enums.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Generate response for request
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
public class ResponseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * return response with default success or error message by status
     * @param resultStatus 返回状态值
     * @return ReturnResponse
     */
    public static ReturnResponse generateResponse(boolean resultStatus) {
        ReturnResponse ReturnResponse = new ReturnResponse();
        if (resultStatus) {
            ReturnResponse
                    .setCode(ResponseCodeEnum.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE);
        } else {
            ReturnResponse
                    .setCode(ResponseCodeEnum.FAIL)
                    .setMessage(CommonConstant.DEFAULT_FAIL_MESSAGE);
        }
        return ReturnResponse;
    }

    /**
     * return response with custom message by status
     * @param message 返回信息
     * @param resultStatus 返回状态值
     * @return ReturnResponse
     */
    public static ReturnResponse generateResponse(String message, boolean resultStatus) {
        ReturnResponse ReturnResponse = new ReturnResponse();
        if (resultStatus) {
            ReturnResponse
                    .setCode(ResponseCodeEnum.SUCCESS)
                    .setMessage(message);
        } else {
            ReturnResponse
                    .setCode(ResponseCodeEnum.FAIL)
                    .setMessage(message);
        }
        return ReturnResponse;
    }

    /**
     * return response with data,if data is null,return no data message,or return data
     * @param data 返回json data
     * @return ReturnResponse
     */
    public static ReturnResponse generateResponse(Object data) {
        ReturnResponse ReturnResponse = new ReturnResponse();
        if (data != null) {
            ReturnResponse
                    .setCode(ResponseCodeEnum.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE)
                    .setData(data);
        } else {
            ReturnResponse
                    .setCode(ResponseCodeEnum.SUCCESS)
                    .setMessage(CommonConstant.NO_RESULT_MESSAGE);

        }
        return ReturnResponse;
    }

    /**
     * Handler response information
     * @param response HttpServletResponse
     * @param object 返回对象
     * @return
     */
    public static HttpServletResponse handlerResponse(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(ResponseCodeEnum.SUCCESS.code);
        try {
            response.getWriter().write(JsonUtil.toJSONString(object));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return response;
    }
}
