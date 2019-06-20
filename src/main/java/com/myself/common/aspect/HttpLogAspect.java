package com.myself.common.aspect;

import com.myself.utils.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求返回记录日志切面
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Aspect
@Component
public class HttpLogAspect {

    public static final Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    /**
     * ThreadLocal时间读取
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 使用注释对全部的controller访问进行日志记录
     */
    @Pointcut("execution(public * com.myself.controller.*.*(..))")
    public void httpLog() {

    }

    /**
      * 日志全记录
      * @param joinPoint 加入切面
      * @return void
      */
    @Before("httpLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //URL
        logger.debug("url:{}", request.getRequestURL());
        //Request method
        logger.debug("method:{}", request.getMethod());
        //IP
        logger.debug("ip:{}", request.getRemoteAddr());
        //Class method name
        logger.debug("method:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // Argument
        logger.debug("args:{}", JsonUtil.toJSONString(joinPoint.getArgs()));
    }

    /**
      * 时间记录
      * @return void
      */
    @After("httpLog()")
    public void doAfter() {
        logger.debug("request cost time:{} ms", System.currentTimeMillis() - startTime.get());
    }

    /**
      * 结束切面后执行，查看返回的对象json值
      * @param object 返回对象
      * @return void
      */
    @AfterReturning(returning = "object", pointcut = "httpLog()")
    public void afterReturning(Object object) {
        logger.debug("response:{}", JsonUtil.toJSONString(object));
    }
}
