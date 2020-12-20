package home.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/19 下午 07:34
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/web")
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping(value = "/home")
    public String showPage(Map<String, Object> map) {
        logger.info("web客户端连接成功...");
        map.put("version", "1.0.0");
        map.put("users", Arrays.asList("张三", "李四", "王五"));
        return "home";
    }
}
