package home.mybatis.service.impl;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/12 下午 07:41
 * @Version: 1.0
 * @Description:
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyPageInterceptor implements Interceptor {

    private int page;
    private int size;
    @SuppressWarnings("unused")
    private String dbType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        while (metaObject.hasGetter("h")) {
            Object object = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(object);
        }
        while (metaObject.hasGetter("target")) {
            Object object = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(object);
        }
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();
        if (mapId.matches(".+ByPager$")) {
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            Map<String, Object> params = (Map<String, Object>) parameterHandler.getParameterObject();
            page = (int) params.get("page");
            size = (int) params.get("size");
            String sql = (String) metaObject.getValue("delegate.boundSql.sql");
            sql += " limit " + (page - 1) * size + "," + size;
            metaObject.setValue("delegate.boundSql.sql", sql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String limit = properties.getProperty("limit", "10");
        this.page = Integer.parseInt(limit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
