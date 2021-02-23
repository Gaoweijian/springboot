package home.transaction.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@RequestMapping("web")
public class WebController {

    @RequestMapping(value = "home")
    public String showPage(Map<String, Object> map) {
        log.info("web客户端连接成功...");
        map.put("version", "1.0.0");
        map.put("users", Arrays.asList("张三", "李四", "王五"));
        return "home";
    }


}
