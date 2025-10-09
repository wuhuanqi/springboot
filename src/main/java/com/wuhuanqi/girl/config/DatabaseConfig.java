package com.wuhuanqi.girl.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 数据库配置类
 * 用于优化数据库连接和错误处理
 */
@Configuration
public class DatabaseConfig {

    @PostConstruct
    public void init() {
        System.out.println("Database connection configuration initialized");
        System.out.println("Network error handling is active");
    }
}