package home.transaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/11 下午 09:45
 * @Version: 1.0
 * @Description:
 */
@Component
public class SchedulTaskService {

    private final Logger logger = LoggerFactory.getLogger(SchedulTaskService.class);

    @Scheduled(cron = "* * * * * ?")
    public void scheduTaskMethod() {
        logger.info("定时任务调度....................");
    }
}
