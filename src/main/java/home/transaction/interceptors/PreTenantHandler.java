package home.transaction.interceptors;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.stereotype.Component;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/18 下午 02:14
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Component
public class PreTenantHandler implements TenantHandler {

    /**
     * @描述 获取当前租户 id
     * @参数 []
     * @返回值 net.sf.jsqlparser.expression.Expression
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/18
     * @修改人
     */
    @Override
    public Expression getTenantId() {
        log.info("[PreTenantHandler]getTenantId={}", "202000001");
        return new LongValue(202000001);
    }

    /**
     * 租户字段名
     *
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        log.info("[PreTenantHandler]tenant_id={}", "tenant_id");
        return "tenant_id";
    }

    /**
     * 根据表名判断是否进行过滤
     * 忽略掉一些表：如租户表（sys_tenant）本身不需要执行这样的处理
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean doTableFilter(String tableName) {
        log.info("[PreTenantHandler]tableName={}", tableName);
        return false;
    }
}
