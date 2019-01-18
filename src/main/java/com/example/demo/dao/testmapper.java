package com.example.demo.dao;

import com.example.demo.domain.testID;
import org.apache.ibatis.annotations.Mapper;

/**
 * mybatis接口
 */
@Mapper
public interface testmapper {
    /**
     * 获取id
     * @return
     */
    public testID selectID();
}
