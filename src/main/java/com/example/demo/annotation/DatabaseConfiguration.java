package com.example.demo.annotation;


import java.lang.annotation.*;

/**
 * 配置通知
 * by：linjie
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DatabaseConfiguration {
    /**
     * annotation description
     *
     * @return {@link java.lang.String}
     */
    String description() default "";

    /**
     * annotation value ,default value "dataSource"
     *
     * @return {@link java.lang.String}
     */
    String value() default "";

}
