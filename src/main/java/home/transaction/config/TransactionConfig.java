package home.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020/12/18, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2020/12/18 下午 09:17
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class TransactionConfig {

    /**
     * 1.创建事务管理器对象
     * 2.配置事务通知
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManagerDev")
    public PlatformTransactionManager getPlatformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
