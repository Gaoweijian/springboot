package home.transaction.interceptors;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/19 上午 11:17
 * @Version: 1.0
 * @Description: prepare/query
 */
@Slf4j
@Component
@Intercepts(@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}))
//@Intercepts({@Signature(
//        type = StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class, Integer.class}
//)})
public class MybatisTenantInterceptor extends PaginationInterceptor {

    @Autowired
    private PreTenantHandler preTenantHandler;

    @PostConstruct
    public void init() {
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        // 多租户拦截
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(preTenantHandler);
        sqlParserList.add(tenantSqlParser);
        this.setSqlParserList(sqlParserList);
    }
}
