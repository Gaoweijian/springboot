import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

//        List<Integer> messages = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//        batchSendMessage(messages);

        log.info(convertStr(null));
        log.info(convertStr(BigDecimal.valueOf(1470.82)));
        log.info(convertStr(new User("1", "gaoweijian")));

//        SalesVolumeMessageData data = new SalesVolumeMessageData();
//        data.setMonthAmount(new BigDecimal(1457.25));
//        data.setMonthTurnoverRatio(null);
//        data.setEstimateTurnoverRatio(157.23D);
//        log.info(constructAlertContent(data));

//        HashMap map = null;
//        map.keySet().forEach(o -> {
//            log.info("{}", o);
//        });

//        Map map = new HashMap<>();
//        map.put("int",null);
//
//        BigDecimal bigDecimal = (BigDecimal) map.get("int");
//        log.info("{}",bigDecimal);

//        Map<String, List<User>> map = new HashMap() {
//            {
//                put("gao", Arrays.asList(new User("1", "user")));
//                put("wei", Arrays.asList(new User("2", "user")));
//                put("jian", Arrays.asList(new User("3", "user"), new User("2", "USER2")));
//            }
//        };
//
//
//        List<String> ids = map.keySet().stream().collect(Collectors.toList());
//
//        log.info("{}", ids);
//
//        List<Map<String, String>> listMap = Arrays.asList(
//                new HashMap() {{
//                    put("id", "1");
//                    put("name", "gao");
//                }},
//                new HashMap() {{
//                    put("id", "2");
//                    put("name", "wei");
//                }},
//                new HashMap() {{
//                    put("id", "3");
//                    put("name", "jian");
//                }}
//        );
//
//
//        Map<String, User> userMap = listMap.stream().map(o -> new User(o.get("id"), o.get("name"))).collect(Collectors.toMap(
//                User::getId, o -> o
//        ));
//        log.info("userMap={}", userMap);


//        User user = new User("1", "gaoweijian");
//        User user2 = new User("2", "");
//        BeanUtils.copyProperties(user, user2);
//        user2.setName("zhaoyu-wife");
//        log.info("user={},user2={}", user, user2);


//        log.info("{}", convertDouble(null));
//
//        log.info("{}", convertDouble(0.23258754));
//
//        log.info("{}", convertDouble(1.568654545441231));


    }


    /**
     * @描述 转换
     * @参数 [rate]
     * @返回值 java.lang.Double
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/4
     * @修改人
     */
    private static Double convertDouble(Double rate) {
        if (rate != null) {
            DecimalFormat df = new DecimalFormat("##0.00");
            return Double.parseDouble(df.format(rate * 100));
        }
        return null;
    }

    /**
     * @描述 组装极光弹窗内容
     * @参数 []
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/1
     * @修改人
     */
    public static String constructAlertContent(SalesVolumeMessageData msgData) {
        StringBuilder content = new StringBuilder();
        content.append("截至当天实际:" + convertStr(msgData.getMonthAmount()));
        content.append("\n");
        content.append("当月总达成率:" + convertStr(msgData.getMonthTurnoverRatio()));
        if (msgData.getMonthTurnoverRatio() != null) {
            content.append("%");
        }
        content.append("\n");
        content.append("预估达成率:" + convertStr(msgData.getEstimateTurnoverRatio()));
        if (msgData.getEstimateTurnoverRatio() != null) {
            content.append("%");
        }
        return content.toString();
    }


    private static <T> String convertStr(T t) {
        if (t != null) {
            return String.valueOf(t);
        }
        return "--";
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
