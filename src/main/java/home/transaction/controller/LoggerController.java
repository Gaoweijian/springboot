package home.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/19 下午 02:41
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/log")
public class LoggerController {
    private final Logger logger = LoggerFactory.getLogger(LoggerController.class);

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息 日志级别：
     * 由低到高：trace->debug->info->warn->error
     */
    @RequestMapping(value = "/info")
    public void logTraceRecord() {
        logger.trace("trace 日志打印成功");
        logger.debug("debug 日志打印成功");
        logger.info("info 日志打印成功");
        logger.warn("warn 日志打印成功");
        logger.error("error 日志打印成功");
    }
}
