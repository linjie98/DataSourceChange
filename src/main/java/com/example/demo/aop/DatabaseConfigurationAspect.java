package com.example.demo.aop;

import com.example.demo.annotation.DatabaseConfiguration;
import com.example.demo.util.MultipleDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

/**
 * 数据库配置切面
 * by：linjie
 */
@Aspect
public class DatabaseConfigurationAspect {

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DATASOURCE = "datasource1";

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfigurationAspect.class);

    @Pointcut("@annotation(com.example.demo.annotation.DatabaseConfiguration)")
    public void DBAspect() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint 切点
     */
    @Before("DBAspect()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            MultipleDataSource.setDataSourceKey(getTargetDataSource(joinPoint));
            LOGGER.info("Methods Described:{}", getDescription(joinPoint));
            LOGGER.info("Replace DataSource:{}", getTargetDataSource(joinPoint));
        } catch (Exception e) {
            LOGGER.warn("DataSource Switch Exception:{}", e);
            MultipleDataSource.setDataSourceKey(DEFAULT_DATASOURCE);
        }
    }

    /**
     * 异常通知
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(pointcut = "DBAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            MultipleDataSource.setDataSourceKey(DEFAULT_DATASOURCE);
        } catch (Exception ex) {
            LOGGER.warn("DataSource Switch Exception:{}", e);
        }
    }

    /**
     * 方法后通知
     *
     * @param joinPoint 切点
     */
    @After("DBAspect()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            MultipleDataSource.setDataSourceKey(DEFAULT_DATASOURCE);
            LOGGER.info("Restore Default DataSource:{}", DEFAULT_DATASOURCE);
        } catch (Exception e) {
            LOGGER.warn("Restore Default DataSource Exception:{}", e);
        }
    }

    /**
     * 获取数据源描述
     *
     * @param joinPoint 切点
     * @return DB-Key(数据库)
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String getDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(DatabaseConfiguration.class).description();
                    if (description == null || "".equals(description))
                        description = "Database switch";
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取数据源
     *
     * @param joinPoint 切点
     * @return DB-Key(数据库)
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String getTargetDataSource(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String value = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    value = method.getAnnotation(DatabaseConfiguration.class).value();
                    if (value == null || "".equals(value))
                        value = DEFAULT_DATASOURCE;
                    break;
                }
            }
        }
        return value;
    }

}
