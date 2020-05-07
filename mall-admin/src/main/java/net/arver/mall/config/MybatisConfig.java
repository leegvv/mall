package net.arver.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"net.arver.mall.mapper", "net.arver.mall.dao"})
public class MybatisConfig {
}
