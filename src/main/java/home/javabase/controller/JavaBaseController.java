package home.javabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/27 下午 08:01
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/base")
public class JavaBaseController {
    private final Logger logger = LoggerFactory.getLogger(JavaBaseController.class);

    @RequestMapping(value = "/se/increase")
    public void javaSESelfIncrease() {

        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        logger.info("i=" + i);
        logger.info("j=" + j);
        logger.info("k=" + k);
    }
}
