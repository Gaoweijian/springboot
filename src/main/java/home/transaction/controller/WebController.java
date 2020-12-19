package home.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public String showPage() {
        logger.info("web客户端连接成功...");
        return "home";
    }
}
