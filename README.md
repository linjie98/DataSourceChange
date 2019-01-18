# DataSourceChange
基于Druid实现动态切换数据源(场景:数据源经常变化)

## 使用
#### 1、模拟数据源(datasource1和datasource2)

#### 2、每个数据源都分别创建test1表并在表中创建一个字段(id)并添加数据

#### 3、以上环境完成后，可以根据DataSourceChange中的application.properties配置进行相关配置

#### 4、若需要添加数据源，只需要在application.properties中添加新数据源配置以及在config下的DataSourceConfiguration下通过以下模版将新数据源写入即可
  ```java
    /**
     * 将数据源从配置文件获取并加载到ioc容器
     * prefix:写指定数据源配置文件中的信息
     */
    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource1")
    public DataSource datasource1() {
        return DruidDataSourceBuilder.create().build();
    }
    
#### 5、测试，在测试类DataSourceChangeDemoApplicationTests中执行test即可
   
   


