package home.aspact;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/27 上午 11:25
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Async
@Component
public class TestTask {

    /**
     * @描述 定时任务测试
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/27
     * @修改人 0 0 9 * * ? 每天早上9点触发 (需要配置)
     */
//    @Scheduled(cron = "0/30 * * * * ?")
    public void sendMessage() {
        log.info("[测试定时任务]sendMessage,SUCCESS");
    }
}
