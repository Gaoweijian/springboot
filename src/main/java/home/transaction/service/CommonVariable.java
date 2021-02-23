package home.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/30 下午 07:29
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class CommonVariable {

    public static Map<String, Object> map = new ConcurrentHashMap<>();

    static {
        map.put("key", "value");
    }

    @Async
    public String tryAsyncA() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[tryAsyncA]");
        return "A";
    }

    @Async
    public String tryAsyncB() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[tryAsyncB]");
        return "B";
    }

    @Async
    public String tryAsyncC() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[tryAsyncC]");
        return "C";
    }
}
