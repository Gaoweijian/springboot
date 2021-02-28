package home.transaction.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/28 下午 01:22
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class CallableService<T> implements Callable<T> {

    /**
     * 并行计算器
     */
    private CountDownLatch downLatch;

    /**
     * java8函数接口
     */
    private Supplier<T> supplier;

    /**
     * 容器
     */
    private Map map;

    /**
     * @描述 构造器
     * @参数 [downLatch, supplier]
     * @返回值
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/28
     * @修改人
     */
    public CallableService(CountDownLatch downLatch, Supplier<T> supplier, Map map) {
        this.downLatch = downLatch;
        this.supplier = supplier;
        this.map = map;
    }

    /**
     * @描述
     * @参数 []
     * @返回值 org.apache.poi.ss.formula.functions.T
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/28
     * @修改人
     */
    @Override
    public T call() throws Exception {
        try {
            T result = supplier.get();
            map.put(result.getClass(), result);
            log.info("[CountDownLatch]并行获取,SUCCESS,result={}", result);
            TimeUnit.SECONDS.sleep(2);
            return result;
        } catch (Exception e) {
            log.error("[CountDownLatch]并行获取,FAIL", e);
            throw e;
        } finally {
            downLatch.countDown();
        }
    }
}
