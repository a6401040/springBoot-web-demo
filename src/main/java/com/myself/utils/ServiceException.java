package com.myself.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * ServiceException
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceException extends Exception {

    /**
     *
     * @param msg
     * @param e
     */
    public ServiceException(String msg, Exception e) {
        super(msg + "\n" + e.getMessage());
    }

    /**
     *
     * @param msg
     */
    public ServiceException(String msg) {
        super(msg);
    }
}
