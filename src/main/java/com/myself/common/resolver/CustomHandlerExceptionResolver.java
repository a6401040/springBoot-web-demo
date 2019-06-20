package com.myself.common.resolver;

import com.myself.common.CommonConstant;
import com.myself.common.ReturnResponse;
import com.myself.common.enums.ResponseCodeEnum;
import com.myself.utils.ResponseUtil;
import com.myself.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据返回的状态码，做视图解析，即隐藏400，401，500等错误状态，返回json
 * Custom exception handler
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    private final Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ReturnResponse returnResponse = new ReturnResponse();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //Service exception,handler exception from service
            if (ex instanceof ServiceException) {
                returnResponse.setCode(ResponseCodeEnum.SUCCESS).setMessage(ex.getMessage());
                logger.warn(ex.getMessage());
            } else {
                //DB exception
                if (ex instanceof DataAccessException) {
                    returnResponse.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR)
                            .setMessage(CommonConstant.DB_ERROR_MESSAGE);
                } else {
                    //Others exception
                    returnResponse.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR)
                            .setMessage(CommonConstant.SERVER_ERROR_MESSAGE);
                }

                // error message detail
                String message = String.format("interface [%s] has exception,method is %s.%s, exception message is %s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());

                logger.error(message, ex);
            }
        } else {
            if (ex instanceof NoHandlerFoundException) {
                returnResponse.setCode(ResponseCodeEnum.NOT_FOUND).setMessage("interface [" + request.getRequestURI() + "] not exist");
            } else {
                returnResponse.setCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR).setMessage(ex.getMessage());
                logger.error(ex.getMessage(), ex);
            }
        }

        ResponseUtil.handlerResponse(response, returnResponse);
        return new ModelAndView();
    }
}
