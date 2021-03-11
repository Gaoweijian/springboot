package home.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/12 下午 07:51
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class PageHelperConfig {

    @Bean(value = "pageHelper")
    public PageHelper pageInterceptor() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        properties.put("reasonable", "true");
        properties.put("supportMethodsArguments", "true");
        properties.put("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
