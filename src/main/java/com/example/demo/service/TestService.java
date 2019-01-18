package com.example.demo.service;

/**
 * 更换数据源demo接口类
 */
public interface TestService {
    /**
     * 切换数据源
     * @param datasource
     * @return
     */
    String ChangeDataSource(String datasource);

    /**
     * 获取数据源中的test1表的id
     */
    void SelectID();
}
