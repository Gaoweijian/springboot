import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/26 下午 03:37
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class ApplicationRunDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunDemo.class);

    private static int visibleNum = 0;

    public static void main(String[] args) {

//        vildateVisible();
//        streamTry();
//        List<User> uList1 = Arrays.asList(new User("1", "GAO"), new User("2", "wei"), new User("3", "jian"));
//        List<User> uList2 = uList1.stream().filter(o -> o.getId().equals("1")).collect(Collectors.toList());

        List<Integer> messages = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        batchSendMessage(messages);
    }

    /**
     * @描述 批量发送消息 注:发送消息的类型必须相同
     * @参数 [messages]
     * @返回值 MessageCenterResponse
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/30
     * @修改人
     */
    public static void batchSendMessage(List<Integer> messages) {
        log.info("[消息中心]批量发送消息,START,messages={}", messages);
        if (messages.size() < 3) {
            log.info("[消息中心]messages={}", messages);
        } else {
            log.info("[消息中心]messages={}", messages.subList(0, 3));
            batchSendMessage(messages.subList(3, messages.size()));
        }
    }


    public static void streamTry() {
        List<User> uList1 = Arrays.asList(new User("1", "GAO"), new User("2", "wei"), new User("3", "jian"));

        List<User> uList2 = Arrays.asList(new User("1", "GAO"), new User("2", "wei"), new User("3", "jian"));

        uList1.stream().forEach(u1 -> {
            uList2.stream().forEach(u2 -> {
                if (u2.getId().equalsIgnoreCase(u1.id)) {
                    u1.setName("匹配到了");
                }
            });
            logger.info(u1.toString());
        });

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
