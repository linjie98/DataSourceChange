package com.example.demo;

import com.example.demo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceChangeDemoApplicationTests {

    @Autowired
    TestService testService;



    @Test
    public void contextLoads() {
    }

    /**
     * 测试利用工具类切换数据源
     */
    @Test
    public void test(){
        //切换数据源2
        testService.ChangeDataSource("datasource2");
        testService.SelectID();
    }

}

