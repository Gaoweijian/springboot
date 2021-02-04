package home.design.factory.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 05:21
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "factory")
public class FactoryController {

    public void excute(JSON json, Consumer consumer) {
        consumer.accept(json);
    }

}
