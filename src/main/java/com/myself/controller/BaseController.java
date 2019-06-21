package com.myself.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证是否发布成功
 */
@RestController
public class BaseController {

    /**
     * Root path, The HEAD method is for SpringBoot Admin to monitor application status
     *
     * @return
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public String root() {
        return "Hello World";
    }
}
