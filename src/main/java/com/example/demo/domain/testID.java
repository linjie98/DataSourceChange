package com.example.demo.domain;

/**
 * 测试实体类
 */
public class testID {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "testID{" +
                "id=" + id +
                '}';
    }
}
