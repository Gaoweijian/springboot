import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/26 下午 03:37
 * @Version: 1.0
 * @Description:
 */
public class ApplicationRunDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunDemo.class);

    private static int visibleNum = 0;

    public static void main(String[] args) {
        vildateVisible();
    }

    public static int vildateVisible() {
//         return validateVisibleTry(status);
//         启动2个线程
        //创建线程1
        new Thread(() -> {
            while (visibleNum == 0) {
//                logger.info("循环中/ visibleNum=" + visibleNum);
            }
            logger.info("跳出循环/ visibleNum=" + visibleNum);
        }, "AAA").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建线程2
        new Thread(() -> {
            visibleNum = 100;
            logger.info("线程BBB赋值完成/ visibleNum=" + visibleNum);
        }, "BBB").start();

        //使用主线程
        return visibleNum;
    }
}