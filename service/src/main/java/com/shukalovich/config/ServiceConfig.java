package com.shukalovich.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(DatabaseConfig.class)
@ComponentScan("com.shukalovich")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceConfig {

}
