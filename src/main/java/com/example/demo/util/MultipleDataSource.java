package com.example.demo.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 切换数据源工具类
 * by：linjie
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    /**
     * 获取当前数据源
     */
    public static String getDataSourceKey(){
        //System.out.println(dataSourceKey.get());
        return dataSourceKey.get();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        // TODO Auto-generated method stub
        return dataSourceKey.get();
    }

    /**
     * 清除当前数据源
     */
    public static void clear() {
        dataSourceKey.remove();
    }
}

