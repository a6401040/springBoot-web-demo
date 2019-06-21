package com.myself.common.datasource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 注册数据源配置服务基类
 * @author Kylin
 * @date 2019/6/21
 * @description: TODO
 * @version: V1.0
 */
public abstract class AbstractMyBatisPlusConfig {

    /**
     * 自动获取数据源方法
     * @return
     */
    public abstract DataSource dynamicDataSource();

    /**
      * 注册MybatisSqlSessionFactoryBean 替代 SqlSessionFactoryBean
      * 实现对Mybatis-Plus 中基本的 Mapper.xml 的过滤
      * @return com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean
      */
    protected MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException{
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        // Here to config mybatis
        mybatisPlus.setTypeAliasesPackage("com.myself.mapper");
        mybatisPlus.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mappers/**Mapper.xml"));
        // Here is very important, if don't config this, will can't switch datasource
        mybatisPlus.setDataSource(dynamicDataSource());
        mybatisPlus.setDataSource(dynamicDataSource());
        mybatisPlus.setVfs(SpringBootVFS.class);

        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisPlus.setConfiguration(mc);

        return mybatisPlus;
    }

    /**
      * 多数据源配置事务管理器
      * @return org.springframework.transaction.PlatformTransactionManager
      */
    protected PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
