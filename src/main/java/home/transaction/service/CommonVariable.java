package home.transaction.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/30 下午 07:29
 * @Version: 1.0
 * @Description:
 */
public class CommonVariable {

    public static Map<String, Object> map = new ConcurrentHashMap<>();

    static {
        map.put("key", "value");
    }
}
