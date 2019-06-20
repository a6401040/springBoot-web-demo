package com.myself.common.aspect;

import com.myself.common.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Multiple DataSource Aspect多数据源切面
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private final String[] QUERY_PREFIX = {"get","select","query"};

    /**
     * 使用注释对全部的dao访问进行数据源切面选择操作
     */
    @Pointcut("execution( * com.myself.dao.*.*(..))")
    public void daoAspect() {
    }

    /**
     * 切换数据源
     *
     * @param point 数据源节点
     */
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName());
        if (isQueryMethod) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
            logger.debug("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceEnum(), point.getSignature());
        }
    }

    /**
     * Restore 数据源
     *
     * @param point the point
     */
    @After("daoAspect()")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceEnum();
        logger.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceEnum(), point.getSignature());
    }


    /**
     * 根据方法名是否get判断是否为读方法
     *
     * @param methodName 查询方法
     * @return
     */
    private Boolean isQueryMethod(String methodName) {
        for (String prefix : QUERY_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

}
