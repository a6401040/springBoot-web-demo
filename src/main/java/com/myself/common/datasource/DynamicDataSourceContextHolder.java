package com.myself.common.datasource;


import com.myself.common.enums.DataSourceMultiEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 多数据源容器
 * @author zhangqiling
 * @date 2019/6/20
 * @version V1.0
 */
public class DynamicDataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static int counter = 0;

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceMultiEnum.master::name);


    /**
     * All DataSource List
     */
    public static List<Object> dataSourceEnums = new ArrayList<>();

    /**
     * The constant slaveDataSourceEnums.
     */
    public static List<Object> slaveDataSourceEnums = new ArrayList<>();

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceEnum(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * Use master data source.
     */
    public static void useMasterDataSource() {
        CONTEXT_HOLDER.set(DataSourceMultiEnum.master.name());
    }

    /**
     * Use slave data source.
     */
    public static void useSlaveDataSource() {
        try {
            int DataSourceEnumIndex = counter % slaveDataSourceEnums.size();
            CONTEXT_HOLDER.set(String.valueOf(slaveDataSourceEnums.get(DataSourceEnumIndex)));
            counter++;
        } catch (Exception e) {
            logger.error("Switch slave datasource failed, error message is {}", e.getMessage());
            useMasterDataSource();
            e.printStackTrace();
        }
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceEnum() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceEnum() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * Check if give DataSource is in current DataSource list
     *
     * @param key the key
     * @return boolean boolean
     */
    public static boolean containDataSourceEnum(String key) {
        return dataSourceEnums.contains(key);
    }
}
