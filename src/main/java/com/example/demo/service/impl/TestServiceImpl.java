package com.example.demo.service.impl;

import com.example.demo.annotation.DatabaseConfiguration;
import com.example.demo.dao.testmapper;
import com.example.demo.service.TestService;
import com.example.demo.util.MultipleDataSource;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 更换数据源demo接口实现类
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    testmapper testmapper;


    /**
     * 更换数据源
     * @param datasource
     * @return
     */
    @Override
    public String ChangeDataSource(String datasource) {
        try {
            //切换数据源，对中间库操作
            MultipleDataSource.setDataSourceKey(datasource);
            //控制台打印当前数据源
            System.out.println(MultipleDataSource.getDataSourceKey());
        } catch (DataAccessException e) {
            throw e;
        } finally {
            return "ok";
        }
    }

    /**
     * 获取id值
     */
    @Override
    public void SelectID() {
        System.out.println(testmapper.selectID());
    }
}
