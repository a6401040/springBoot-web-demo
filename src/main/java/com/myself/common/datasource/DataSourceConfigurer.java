package com.myself.common.datasource;
;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置器
 * 单个数据源
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@Configuration
@PropertySource(value = "classpath:config/config.properties", encoding = "UTF-8")
public class DataSourceConfigurer extends AbstractMyBatisPlusConfig{

    /**
     * single DataSource
     *
     * @return data source
     */
    @Bean("hikari")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource hikari() {
        return DataSourceBuilder.create().build();
    }


    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    @Override
    public DataSource dynamicDataSource() {
        //直接设置单库
        return hikari();
    }

    /**
     * MybatisSqlSession factory bean.
     * Here to config datasource for MybatisSqlSessionFactory
     * <p>
     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
     * {@code 'application.config'} file, or there will appear invalid bond statement exception
     *
     * @return the sql session factory bean
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    @Override
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws IOException {
        return super.mybatisSqlSessionFactoryBean();
    }

    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    @Override
    public PlatformTransactionManager transactionManager() {
        return super.transactionManager();
    }
}

