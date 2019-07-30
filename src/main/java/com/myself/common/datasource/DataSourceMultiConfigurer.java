package com.myself.common.datasource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.myself.common.enums.DataSourceMultiEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 一主多重数据源配置器
 * 改用 一主多重 请添加@Configuration 并注释掉DataSourceConfigurer中的@Configuration
 * * @Configuration
 * * @PropertySource(value = "classpath:config-multi.properties", encoding = "UTF-8")
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
@SuppressWarnings("unused")
public class DataSourceMultiConfigurer extends AbstractMyBatisPlusConfig{

    /**
     * master DataSource
     *
     * @return data source
     */
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Slave data source.
     *
     * @return the data source
     */
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slave() {
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
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceMultiEnum.master.name(), master());
        dataSourceMap.put(DataSourceMultiEnum.slave.name(), slave());


        // Set master datasource as default
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // Set master and slave datasource as target datasource
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // To put datasource keys into DataSourceContextHolder to judge if the datasource is exist
        DynamicDataSourceContextHolder.dataSourceEnums.addAll(dataSourceMap.keySet());

        // To put slave datasource keys into DataSourceContextHolder to load balance
        DynamicDataSourceContextHolder.slaveDataSourceEnums.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.slaveDataSourceEnums.remove(DataSourceMultiEnum.master.name());
        return dynamicRoutingDataSource;
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